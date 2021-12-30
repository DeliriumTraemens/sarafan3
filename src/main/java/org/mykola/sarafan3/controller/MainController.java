package org.mykola.sarafan3.controller;

import org.mykola.sarafan3.domain.User;
import org.mykola.sarafan3.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	MessageRepository messageRepo;
	@Value("${spring.profiles.active}")
	private String profile;
	
	@GetMapping
	public String mainPage(Model model, @AuthenticationPrincipal User user){
		
		HashMap<Object, Object> data = new HashMap<>();
		
		
		if (user != null) {
			data.put("profile", user);
			data.put("messages", messageRepo.findAll());
		}
		
		data.put("profile", user);
		data.put("messages", messageRepo.findAll());
		model.addAttribute("frontendData", data);
		model.addAttribute("isDevMode","dev".equals(profile));
		
		return "index";
	}
}
