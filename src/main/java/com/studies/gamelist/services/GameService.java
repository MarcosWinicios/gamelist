package com.studies.gamelist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studies.gamelist.dto.GameDTO;
import com.studies.gamelist.dto.GameMinDTO;
import com.studies.gamelist.entities.Game;
import com.studies.gamelist.projections.GameMinProjection;
import com.studies.gamelist.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll() {
		return gameRepository.findAll().stream().map(game -> new GameMinDTO(game)).toList();
	}

	@Transactional(readOnly = true)
	public GameDTO findById(Long id) {
		Optional<Game> result = gameRepository.findById(id);

		if (result.isPresent()) {
			Game game = result.get();
			return new GameDTO(game);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(Long id){
		return gameRepository.searchByList(id)
				.stream()
				.map(game -> new GameMinDTO(game)).toList();
	}
}
