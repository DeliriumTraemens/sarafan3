package org.mykola.sarafan3.controller;

import com.nimbusds.jose.shaded.json.JSONObject;
import org.mykola.sarafan3.domain.Category;
import org.mykola.sarafan3.repository.CategoryRepository;
import org.mykola.sarafan3.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
	private final CategoryService catService;
	private final CategoryRepository catRepo;
	
	@Autowired
	public CategoryController(CategoryService catService, CategoryRepository catRepo) {
		this.catService = catService;
		this.catRepo = catRepo;
	}
	
	@GetMapping
	public List<Category> getAll(){
		System.out.println("\n============= CAT LIST =============");
		System.out.println(catService.getAll());
		return catService.getAll();
	}
	
	@PostMapping
	public Category saveNewCategory(Category category) throws IOException {
		if(category==null){
			 System.out.println("\n\n ========= CATEGORY IS NULL ==============");
			return null;
		} else {
			System.out.println("\n\n ------------+++++++++Passed to Service");
			return catService.saveNewCategory(category);
		}
		
	}


}//EndOfClass
