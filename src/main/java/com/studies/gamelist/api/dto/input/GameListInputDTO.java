package com.studies.gamelist.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameListInputDTO {
	
	@NotBlank
	private String name;
}
