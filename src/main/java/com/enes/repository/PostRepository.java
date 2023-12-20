package com.enes.repository;

import com.enes.repository.entity.Category;
import com.enes.repository.entity.Post;
import com.enes.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
    List<Post> findAllByUser(User user);

    List<Post> findAllByCategory(Category byIdCategory);
}
