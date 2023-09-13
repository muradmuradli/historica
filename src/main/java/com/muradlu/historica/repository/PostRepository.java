package com.muradlu.historica.repository;

import com.muradlu.historica.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
