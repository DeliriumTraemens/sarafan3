package org.mykola.sarafan3.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long parentCatId;
	private String name;
	private String description;
	private String shortDescription;
	private LocalDateTime creationDate;
	private String image;
	
	private boolean root;
	
	@JsonIgnore
	@ManyToOne
	private Category parentCat;
	
	@OneToMany(mappedBy="parentCat")
	private Set<Category> childrenSet = new HashSet<Category>();
	
	
	
}
