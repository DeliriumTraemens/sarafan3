package org.mykola.sarafan3.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
@Data
@EqualsAndHashCode(of = {"id"})
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.IdName.class)
	private Long id;
	
	@JsonView(Views.IdName.class)
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "message_id")//Необязательное, но Желательное название поля в кортеже Коммента
	private Message message;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	@JsonView(Views.FullMessage.class)
	private User author;
	
	
	
}
