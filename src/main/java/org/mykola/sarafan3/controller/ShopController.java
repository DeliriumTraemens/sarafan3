package org.mykola.sarafan3.controller;

import org.mykola.sarafan3.domain.Item;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
@CrossOrigin("*")
public class ShopController {

	@PostMapping("/add")
	public void addItem(@RequestBody Item item){
		System.out.println("\n---------- New Item --------");
		System.out.println(item);
		System.out.println("------------------");
	}
	
}
