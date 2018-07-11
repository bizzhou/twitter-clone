package com.clone.twitter.repository;

import com.clone.twitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmailAndPassword(String email, String hashedPassword);
}
