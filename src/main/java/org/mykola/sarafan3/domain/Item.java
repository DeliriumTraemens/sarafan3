package org.mykola.sarafan3.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name ="item")
public class Item {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	private String picture;
}
