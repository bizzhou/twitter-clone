package com.clone.twitter.repository;


import com.clone.twitter.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwitterRepository extends JpaRepository<Post, Long> {
}
