package org.mykola.sarafan3.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mykola.sarafan3.domain.Message;
import org.mykola.sarafan3.domain.Views;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
//@ToString()
@JsonView(Views.FullMessage.class)
public class MessagePageDto {
	
	private List<Message> messages;
	private int currentPage;
	private int totalPages;
	
	@Override
	public String toString() {
		return "MessagePageDto{" +
				       "messages=" + messages +
				       ", currentPage=" + currentPage +
				       ", totalPages=" + totalPages +
				       '}';
	}
}
