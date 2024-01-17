package com.studies.gamelist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studies.gamelist.dto.GameMinDTO;
import com.studies.gamelist.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	public List<GameMinDTO> findAll() {

		return gameRepository.findAll().stream().map(game -> new GameMinDTO(game)).toList();

	}
}
