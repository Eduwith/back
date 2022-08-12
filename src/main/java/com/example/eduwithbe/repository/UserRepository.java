package com.example.eduwithbe.repository;

import com.example.eduwithbe.domain.UserEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository @Primary
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //UserEntity findByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

}