package org.mykola.sarafan3.controller;

import org.mykola.sarafan3.domain.Item;
import org.mykola.sarafan3.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/item")
public class ItemController {
	@Value("${upload.path}")
	private String uploadPath;
	
	private final ItemRepository itemRepo;
	
	public ItemController(ItemRepository itemRepo) {
		this.itemRepo = itemRepo;
	}
	
	@GetMapping
	public List<Item> getItems(){
		return itemRepo.findAll();
	}
	
	@GetMapping("{id}")
	public Item getOneItem(@PathVariable("id")Long id){
		System.out.println("\n----------------------");
		System.out.println(itemRepo.findById(id).get());
		return itemRepo.findById(id).get();
	}
	
	
}
