package org.mykola.sarafan3.service;

import org.mykola.sarafan3.domain.Category;
import org.mykola.sarafan3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {
	private final CategoryRepository catRepo;
	
	@Autowired
	public CategoryService(CategoryRepository catRepo) {
		this.catRepo = catRepo;
	}
	
	public List<Category> getAll() {
		return catRepo.findAll();
	}
	
	
	public Category saveCategory(String name, String description) {
		Category category = new Category();
		category.setName(name);
		category.setDescription(description);
		category.setCreationDate(LocalDateTime.now());
		
		if(category.getParent()==null){
			category.setRoot(true);
		}
		
		System.out.println("\n\n=======================CATEGORY TO SAVE==========");
		System.out.println(category);
		return catRepo.save(category);
	}
	
	public Category saveNewCategory(Category category) {
		
		category.setCreationDate(LocalDateTime.now());
		
		if(category.getParentCat()==null){
			category.setRoot(true);
		}
		
		return catRepo.save(category);
	}
}
