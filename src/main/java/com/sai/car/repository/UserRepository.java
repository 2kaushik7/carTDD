package com.sai.car.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sai.car.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByName(String name);

}
