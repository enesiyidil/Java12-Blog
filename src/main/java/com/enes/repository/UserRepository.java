package com.enes.repository;

import com.enes.repository.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Primary
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String userEmail);
}
