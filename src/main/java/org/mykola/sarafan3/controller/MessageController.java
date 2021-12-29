package org.mykola.sarafan3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.mykola.sarafan3.domain.Message;
import org.mykola.sarafan3.domain.Views;
import org.mykola.sarafan3.repository.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
	private final MessageRepository messageRepo;
	
	public MessageController(MessageRepository messageRepo) {
		this.messageRepo = messageRepo;
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
		return messageRepo.save(message);
	}
	
	@PutMapping("{id}")
	public Message updateOne(@PathVariable("id") Message messageFromDb,
	                         @RequestBody Message message){
		BeanUtils.copyProperties(message, messageFromDb, "id");
		return messageRepo.save(messageFromDb);
	}
	
	@DeleteMapping("{id}")
	public void deleteOne(@PathVariable("id") Message message){
		messageRepo.delete(message);
	}
	
	@MessageMapping ("/changeMessage")
	@SendTo("/topic/activity")
	public Message message(Message message){
		
		message.setCreationDate(LocalDateTime.now());
		
		System.out.println("\n--------------------");
		System.out.println("message From site");
		System.out.println("\n--------------------");
		System.out.println(message);
		System.out.println("\n--------------------");
		
		return messageRepo.save(message);
	}
	
	
}
