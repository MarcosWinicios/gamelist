package com.studies.gamelist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studies.gamelist.entities.Game;
import com.studies.gamelist.projections.GameMinProjection;

public interface GameRepository extends JpaRepository<Game, Long> {

	@Query(nativeQuery = true, value = 
			"SELECT tg.id, tg.title, tg.game_year AS gameYear, tg.img_url AS imgUrl, tg.short_description AS shortDescription, tb.position FROM tb_game AS tg INNER JOIN tb_belonging AS tb ON tb.game_id = tg.id WHERE tb.list_id = :listId ORDER BY tb.position")
	public List<GameMinProjection> searchByList(Long listId);

}
