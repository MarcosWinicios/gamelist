package com.studies.gamelist.api.dto;

import com.studies.gamelist.domain.entities.GameList;

import lombok.Getter;

@Getter
public class GameListDTO {

	private Long id;
	private String name;
	
	public GameListDTO(GameList entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}
	
	
}
