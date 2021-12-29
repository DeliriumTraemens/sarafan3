package org.mykola.sarafan3.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class MesDto {
	@JsonIgnore
	private Long id;
	private String text;
	private LocalDateTime creationDate;
	
	public MesDto() {
	}
	
	public MesDto(Long id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "MesDto{" +
				       "id=" + id +
				       ", text='" + text + '\'' +
				       ", creationDate=" + creationDate +
				       '}';
	}
}
