package com.studies.gamelist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studies.gamelist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {
	


}
