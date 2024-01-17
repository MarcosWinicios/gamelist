package com.studies.gamelist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studies.gamelist.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
	


}
