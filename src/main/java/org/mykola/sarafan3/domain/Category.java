package org.mykola.sarafan3.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Table(name ="category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private String shortDescription;
	private Long parent;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationDate;
	
	private String image;
	
	private boolean root;
	
	@JsonIgnore
	@ManyToOne
	private Category parentCat;
	
	@OneToMany(mappedBy="parentCat")
	private Set<Category> childrenSet = new HashSet<Category>();
	
	
}
