package com.hcl.lms.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

=======
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
>>>>>>> Feature3
import com.hcl.lms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
<<<<<<< HEAD
=======
	// User findByUserId(String userId);
	public Optional<User> findByEmailAndPassword(String email, String password);
>>>>>>> Feature3

}
