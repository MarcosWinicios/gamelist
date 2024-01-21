package com.studies.gamelist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studies.gamelist.dto.GameListDTO;
import com.studies.gamelist.dto.GameMinDTO;
import com.studies.gamelist.dto.ReplacementDTO;
import com.studies.gamelist.services.GameListService;
import com.studies.gamelist.services.GameService;

@RestController
@RequestMapping("/lists")
public class GameListController {
	
	@Autowired
	private GameListService gameListService;
	
	@Autowired
	private GameService gameService;
	
	@GetMapping
	public ResponseEntity<List<GameListDTO>> findAll(){
		var result = gameListService.findAll();
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{listId}/games")
	public ResponseEntity<List<GameMinDTO>> getMethodName(@PathVariable Long listId) {
		var result = gameService.findByList(listId);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/{listId}/replacement")
	public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
		gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
		
		
	}
}
