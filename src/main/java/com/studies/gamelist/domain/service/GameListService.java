package com.studies.gamelist.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studies.gamelist.api.dto.GameListDTO;
import com.studies.gamelist.api.dto.input.GameListInputDTO;
import com.studies.gamelist.domain.entities.GameList;
import com.studies.gamelist.domain.entities.User;
import com.studies.gamelist.domain.repository.GameListRepository;
import com.studies.gamelist.domain.repository.GameRepository;
import com.studies.gamelist.projections.GameMinProjection;

@Service
public class GameListService {

	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;

	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		return gameListRepository.findAll().stream().map(list -> new GameListDTO(list)).toList();
	}
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> list =  gameRepository.searchByList(listId);
		
		GameMinProjection obj = list.remove(sourceIndex);
		list.add(destinationIndex, obj);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		for(int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}
	
	@Transactional
	public GameListDTO save(String userId, GameListInputDTO gameListInput) {
		
		GameList list =  new GameList();
		list.setUser(new User());
		
		list.getUser().setId(userId);
		list.setName(gameListInput.getName());
		
		list = gameListRepository.save(list);
		
		return new GameListDTO(list);
	}

}
