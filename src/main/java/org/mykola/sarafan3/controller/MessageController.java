package org.mykola.sarafan3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.mykola.sarafan3.domain.Item;
import org.mykola.sarafan3.domain.Message;
import org.mykola.sarafan3.domain.User;
import org.mykola.sarafan3.domain.Views;
import org.mykola.sarafan3.dto.MessagePageDto;
import org.mykola.sarafan3.repository.ItemRepository;
import org.mykola.sarafan3.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Value("${upload.path}")
	private String uploadPath;
	
	public static final int MESSAGES_PER_PAGE = 2;
	
	private final MessageService messageService;
	
	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@Autowired
	ItemRepository itemRepo;
	
	@GetMapping
	@JsonView(Views.FullMessage.class)
	public MessagePageDto list(
			@PageableDefault(size = MESSAGES_PER_PAGE, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
	                          ) {
		System.out.println("\n\n------------------------");
		System.out.println("PageaBBLLEE" + pageable);
		System.out.println("------------------------\n\n");
		return messageService.findAll(pageable);
	}
	
	@GetMapping("{id}")
	@JsonView(Views.FullMessage.class)
	public Message getOne(@PathVariable("id") Message message) {
		
		return message;
	}
	
	@PostMapping("/addItem")
	public void addItem(@RequestBody Item item) {
		System.out.println("\n\n -------------- ADD ITEM --------------------");
		System.out.println(item);
		System.out.println("DELIVERED");
	}
	
	
	@PostMapping("/addImage")
	public Item addImage(@RequestParam("file") MultipartFile file, Item item) throws IOException {
		
		if (file != null && ! file.getOriginalFilename().isEmpty()) {
			File uploadDir = new File(uploadPath);
			if (! uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			//Build FileName
			String uuidFile = UUID.randomUUID().toString();
			String resultFileName = uuidFile + "-" + file.getOriginalFilename();
			
			//Transfer and Save
			file.transferTo(new File(uploadPath + "/" + resultFileName));
			
			//Add Picture to Object
			item.setPicture(resultFileName);
		}
		
		
		System.out.println("\n\n -------------- ADD IMAGE --------------------");
		System.out.println(item);
		System.out.println("DELIVERED");
		return itemRepo.save(item);
	}
	
	@PostMapping
	public Message create(
			@RequestBody Message message,
			@AuthenticationPrincipal User user
	                     ) throws IOException {
		return messageService.create(message, user);
	}
	
	@PutMapping("{id}")
	public Message updateOne(
			@PathVariable("id") Message messageFromDb,
			@RequestBody Message message
	                        ) throws IOException {
		return messageService.updateMessage(messageFromDb, message);
	}
	
	@DeleteMapping("{id}")
	public void deleteOne(@PathVariable("id") Message message) {
		messageService.delete(message);
	}
	
	
}//EndOfClass


























