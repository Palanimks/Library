package com.hcl.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.lms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByEmailAndPassword(String email, String password);

}
