package com.example.eduwithbe.UserTest.Repository;

import com.example.eduwithbe.UserTest.Domain.UserTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTestRepository extends JpaRepository<UserTestEntity, String> {
}
