package com.studies.gamelist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studies.gamelist.entities.User;

public interface UserRepository  extends JpaRepository<User, String>{

}
