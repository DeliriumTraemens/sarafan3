package org.mykola.sarafan3.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
//@ToString(of = {"id", "text","creationDate"})
//@EqualsAndHashCode(of = {"id"})
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
//	Constructors
	
	public Message() {
	}

	public Message(Long id, String text) {
		this.id = id;
		this.text = text;
	}

	public Message(Long id, String text, LocalDateTime creationDate) {
		this.id = id;
		this.text = text;
		this.creationDate = creationDate;
	}
	
	//	Get Set
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		id = id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	//EqualsHash
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (! (o instanceof Message)) {
			return false;
		}
		Message message = (Message) o;
		return getId().equals(message.getId()) &&
				       Objects.equals(getText(), message.getText()) &&
				       Objects.equals(getCreationDate(), message.getCreationDate());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId(), getText(), getCreationDate());
	}
	
	//ToString
	
	@Override
	public String toString() {
		return "\nMessage{" +
				       "id=" + id +
				       ", text='" + text + '\'' +
				       ", creationDate=" + creationDate +
				       '}';
	}
}
