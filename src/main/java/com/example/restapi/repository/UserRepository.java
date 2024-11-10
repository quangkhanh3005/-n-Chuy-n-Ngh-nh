package com.example.restapi.repository;

import com.example.restapi.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<user, Integer> {
    Optional<user> findByUsername(String username);
}
