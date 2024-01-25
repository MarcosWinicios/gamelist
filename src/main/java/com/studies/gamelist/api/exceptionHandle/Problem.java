package com.studies.gamelist.api.exceptionHandle;


import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Problem {
	
	private Integer status;
	private OffsetDateTime dateHour;
	private String title;
	private List<Field> fields;
}
