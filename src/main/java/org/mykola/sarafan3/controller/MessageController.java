package org.mykola.sarafan3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mykola.sarafan3.domain.Message;
import org.mykola.sarafan3.domain.User;
import org.mykola.sarafan3.domain.Views;
import org.mykola.sarafan3.dto.EventType;
import org.mykola.sarafan3.dto.MetaDto;
import org.mykola.sarafan3.dto.ObjectType;
import org.mykola.sarafan3.repository.MessageRepository;
import org.mykola.sarafan3.util.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/message")
public class MessageController {
	private static String URL_PATTERN = "https?:\\/\\/?[\\w\\d\\._\\-%\\/\\?=&#]+";
	private static String IMAGE_PATTERN = "\\.(jpeg|jpg|gif|png)$";
	
	private static Pattern URL_REGEX = Pattern.compile(URL_PATTERN, Pattern.CASE_INSENSITIVE);
	private static Pattern IMG_REGEX = Pattern.compile(IMAGE_PATTERN, Pattern.CASE_INSENSITIVE);
	
	private final MessageRepository messageRepo;
	private final BiConsumer<EventType, Message> wsSender;
	
	@Autowired
	public MessageController(MessageRepository messageRepo, WsSender wsSender) {
		this.messageRepo = messageRepo;
		this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.IdName.class);
	}
	
	
	@GetMapping
	@JsonView(Views.IdName.class)
	public List<Message> list(){
		return messageRepo.findAll();
	}
	
	@GetMapping("{id}")
	@JsonView(Views.FullMessage.class)
	public Message getOne(@PathVariable("id") Message message){
//	public Message getOne(@PathVariable("id") Long id){
//		return messageRepo.findById(id).get();
		return message;
	}
	
	
	
	@PostMapping
	public Message create(@RequestBody Message message,
	                      @AuthenticationPrincipal User user) throws IOException {
		
		message.setAuthor(user);
		message.setCreationDate(LocalDateTime.now());
		
		fillMeta(message);
		
		Message updatedMessage = messageRepo.save(message);
		wsSender.accept(EventType.CREATE, updatedMessage);
		return updatedMessage;
	}
	
	@PutMapping("{id}")
	public Message updateOne(@PathVariable("id") Message messageFromDb,
	                         @RequestBody Message message) throws IOException {
		BeanUtils.copyProperties(message, messageFromDb, "id");
		fillMeta(messageFromDb);
		
		Message updatedMessage = messageRepo.save(messageFromDb);
		wsSender.accept(EventType.UPDATE, updatedMessage);
		return updatedMessage;
	}
	
	@DeleteMapping("{id}")
	public void deleteOne(@PathVariable("id") Message message){
		messageRepo.delete(message);
		wsSender.accept(EventType.REMOVE, message);
		
	}
	
	private void fillMeta(Message message) throws IOException {
		String text = message.getText();
		Matcher matcher = URL_REGEX.matcher(text);
		if(matcher.find()){
			String url = text.substring(matcher.start(), matcher.end());
			matcher = IMG_REGEX.matcher(url);
			message.setLink(url);
			
			if(matcher.find()){
			message.setLinkCover(url);
//			} else if (!url.contains("youtu")){
			} else if (url.contains("youtu")){
				MetaDto meta = getMeta(url);
				message.setLinkCover(meta.getCover());
				message.setLinkTitle(meta.getTitle());
				message.setLinkDescription(meta.getDescription());
			}
		}
		
	}
	
	private MetaDto getMeta(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		Elements title = doc.select("meta[name$=title], meta[property$=title]");
		Elements description = doc.select("meta[name$=description], meta[property$=description]");
		Elements cover = doc.select("meta[name$=image], meta[property$=image]");
		
		return new MetaDto(
				getContent(title.first()),
				getContent(description.first()),
				getContent(cover.first())
		);
	}
	
	private String getContent(Element element) {
		return element == null? "" : element.attr("content");
	}
	
}//EndOfClass


























