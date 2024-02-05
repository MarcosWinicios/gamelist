package com.studies.gamelist.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studies.gamelist.domain.entities.User;

@Repository
public interface UserRepository  extends JpaRepository<User, String>{

	public Optional<User> findByEmail(String email);
	
	@Query(nativeQuery = true, value = "SELECT * FROM tb_user WHERE email = :email")
	public Optional<User> loadUserByEmail(String email);
	

}
