package org.mykola.sarafan3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.mykola.sarafan3.domain.Message;
import org.mykola.sarafan3.domain.Views;
import org.mykola.sarafan3.dto.EventType;
import org.mykola.sarafan3.dto.ObjectType;
import org.mykola.sarafan3.repository.MessageRepository;
import org.mykola.sarafan3.util.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("/message")
public class MessageController {
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
	public Message create(@RequestBody Message message){
		message.setCreationDate(LocalDateTime.now());
		Message updatedMessage = messageRepo.save(message);
		wsSender.accept(EventType.CREATE, updatedMessage);
		return updatedMessage;
	}
	
	@PutMapping("{id}")
	public Message updateOne(@PathVariable("id") Message messageFromDb,
	                         @RequestBody Message message){
		BeanUtils.copyProperties(message, messageFromDb, "id");
		
		Message updatedMessage = messageRepo.save(messageFromDb);
		wsSender.accept(EventType.UPDATE, updatedMessage);
		return updatedMessage;
	}
	
	@DeleteMapping("{id}")
	public void deleteOne(@PathVariable("id") Message message){
		messageRepo.delete(message);
		wsSender.accept(EventType.REMOVE, message);
		
	}
	
	private void fillMeta(){}
	
}//EndOfClass
