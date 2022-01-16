package org.mykola.sarafan3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.mykola.sarafan3.domain.User;
import org.mykola.sarafan3.domain.Views;
import org.mykola.sarafan3.dto.MessagePageDto;
import org.mykola.sarafan3.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
	private final MessageService messageService;
	private final ObjectWriter writer;
	
	@Autowired
	public MainController(MessageService messageService, ObjectMapper mapper) {
		this.messageService = messageService;
		
		writer = mapper
                      .setConfig(mapper.getSerializationConfig())
                      .writerWithView(Views.FullMessage.class);
	}
	
	@Value("${spring.profiles.active}")
	private String profile;
	
	
	@GetMapping
//	@JsonView(Views.IdName.class)
	
	public String mainPage(Model model, @AuthenticationPrincipal User user) throws JsonProcessingException {
		
		HashMap<Object, Object> data = new HashMap<>();
		
		
		if (user != null) {
			data.put("profile", user);
			
			Sort sort = Sort.by(Sort.Direction.DESC, "id");
			PageRequest pageRequest = PageRequest.of(0, MessageController.MESSAGES_PER_PAGE, sort);
			MessagePageDto messagePageDto=messageService.findAll(pageRequest);
			
			
			
			String messages = writer.writeValueAsString(messagePageDto.getMessages());
			
			model.addAttribute("messages", messages);
			data.put("currentPage", messagePageDto.getCurrentPage());
			data.put("totalPages", messagePageDto.getTotalPages());
//			model.addAttribute("messages", messageRepo.findAll());
		
		}else{
			model.addAttribute("messages","[]");
		}
		
		data.put("profile", user);
		model.addAttribute("frontendData", data);
		model.addAttribute("isDevMode","dev".equals(profile));
		
		return "index";
	}
}
