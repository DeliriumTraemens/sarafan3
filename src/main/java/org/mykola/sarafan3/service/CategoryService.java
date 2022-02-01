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
//		return catRepo.findByRoot(true);
		return catRepo.findByRootOrderById(true);
	}
	
	
	public Category saveCategory(String name, String description) {
		Category category = new Category();
		category.setName(name);
		category.setDescription(description);
		category.setCreationDate(LocalDateTime.now());
		
		if(category.getParent()==null){
			category.setRoot(true);
		}
		
//		System.out.println("\n\n=======================CATEGORY TO SAVE==========");
//		System.out.println(category);
		return catRepo.save(category);
	}
	
	public Category saveNewCategory(Category category) {
		
		category.setCreationDate(LocalDateTime.now());
		
		if(category.getParentCat()==null){
			category.setRoot(true);
		}
		
		return catRepo.save(category);
	}
	
	public Category addSubCategory(Long parent, String name, String description, String shortDescription) {
		Category parentCat = catRepo.findById(parent).get();
		Category newSubCategory = new Category();
		
		newSubCategory.setParentCat(parentCat);
		newSubCategory.setParent(parent);
		newSubCategory.setName(name);
		newSubCategory.setDescription(description);
		newSubCategory.setShortDescription(shortDescription);
		newSubCategory.setCreationDate(LocalDateTime.now());
		
//		System.out.println("\n=========SUB CAT==========");
//		System.out.println(newSubCategory +"\n");
		
		return catRepo.save(newSubCategory);
	}
	
	public Category patchCategory(Long id, String name, String description) {
		Category catFromDb = catRepo.findById(id).get();
		if (!name.equals(""))
		{
			catFromDb.setName(name);
		}
		if(!description.equals(""))
		{
			catFromDb.setDescription(description);
		}
		
		return catRepo.save(catFromDb);
	}
}
