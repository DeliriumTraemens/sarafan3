package org.mykola.sarafan3.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Category {
	
	private Long id;
	private Long parentCatId;
	private String name;
	private String description;
	private String shortDescription;
	private LocalDateTime creationDate;
	private String image;
	
	private boolean root;
	
	private Category parent;
	
	private Set<Category> childrenSet = new HashSet<Category>();
	
	
	
}
