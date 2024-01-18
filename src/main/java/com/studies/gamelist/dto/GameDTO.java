package com.studies.gamelist.dto;

import org.springframework.beans.BeanUtils;

import com.studies.gamelist.entities.Game;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class GameDTO {

	private Long id;
	private String title;
	private Integer year;
	private String genre;
	private String platforms;
	private Double score;
	private String imgUrl;
	private String shortDescription;
	private String longDescription;

	public GameDTO(Game entity) {
		BeanUtils.copyProperties(entity, this);
	}

}
