package com.studies.gamelist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.studies.gamelist.dto.GameDTO;
import com.studies.gamelist.dto.GameMinDTO;
import com.studies.gamelist.entities.Game;
import com.studies.gamelist.services.GameService;

@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@GetMapping
	public ResponseEntity<List<GameMinDTO>> findAll(){
		var result = gameService.findAll();
		
		return ResponseEntity.ok(result);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<GameDTO> findById(@PathVariable Long id){
		
		GameDTO result = gameService.findById(id);
		if(result == null) {
			return null;
		}
		return ResponseEntity.ok(result);
	}

}
