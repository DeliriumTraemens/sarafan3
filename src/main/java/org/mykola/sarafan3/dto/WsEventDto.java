package org.mykola.sarafan3.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.mykola.sarafan3.domain.Views;

@Data
@AllArgsConstructor
@JsonView(Views.IdShow.class)
public class WsEventDto {
	private ObjectType objecttType;
	private EventType eventType;
	
	@JsonRawValue
	private String body;
}
