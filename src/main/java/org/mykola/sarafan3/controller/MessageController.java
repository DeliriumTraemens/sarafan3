package org.mykola.sarafan3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.mykola.sarafan3.domain.Message;
import org.mykola.sarafan3.domain.User;
import org.mykola.sarafan3.domain.Views;
import org.mykola.sarafan3.dto.MessagePageDto;
import org.mykola.sarafan3.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	public static final int MESSAGES_PER_PAGE = 3;
	
	private final MessageService messageService;
	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	
	@GetMapping
	@JsonView(Views.FullMessage.class)
	public MessagePageDto list(
			@PageableDefault(size = MESSAGES_PER_PAGE,sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
		System.out.println("\n\n------------------------");
		System.out.println("PageaBBLLEE"+pageable);
		System.out.println("------------------------\n\n");
		return messageService.findAll(pageable);
	}
	
	@GetMapping("{id}")
	@JsonView(Views.FullMessage.class)
	public Message getOne(@PathVariable("id") Message message){

		return message;
	}
	
	
	@PostMapping
	public Message create(@RequestBody Message message,
	                      @AuthenticationPrincipal User user) throws IOException {
		return messageService.create(message, user);
	}
	
	@PutMapping("{id}")
	public Message updateOne(@PathVariable("id") Message messageFromDb,
	                         @RequestBody Message message) throws IOException {
		return messageService.updateMessage(messageFromDb,message);
	}
	
	@DeleteMapping("{id}")
	public void deleteOne(@PathVariable("id") Message message){
		messageService.delete(message);
	}
	
	
}//EndOfClass


























