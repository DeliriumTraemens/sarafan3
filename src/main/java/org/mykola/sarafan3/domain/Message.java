package org.mykola.sarafan3.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Data
@ToString(of = {"id", "text", "creationDate"})
@EqualsAndHashCode(of = {"id"})
public class Message implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.IdShow.class)
	private Long id;
	
	@JsonView(Views.IdName.class)
	private String text;
	
	@Column(updatable=false)
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-DD HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonView(Views.FullMessage.class)
	private LocalDateTime creationDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id") //Необязательное, но Желательное название поля в кортеже Коммента
	@JsonView(Views.FullMessage.class)
	private User author;
	
	@OneToMany(mappedBy="message", orphanRemoval = true) //Название поля в кортеже Коммента, ссылающееся на ид объекта Мессадж; orphanRemoval - прибивает все комменты
	// при удалении объекта сообщения
	@JsonView(Views.FullMessage.class)
	private List<Comment> comments;
	
	@JsonView(Views.FullMessage.class)
	private String link;
	@JsonView(Views.FullMessage.class)
	private String linkTitle;
	@JsonView(Views.FullMessage.class)
	private String linkDescription;
	@JsonView(Views.FullMessage.class)
	private String linkCover;
	

}//EndOfClass
