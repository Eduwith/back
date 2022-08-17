package com.example.eduwithbe.UserTest.Repository;

import com.example.eduwithbe.UserTest.Domain.UserTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTestRepository extends JpaRepository<UserTestEntity, String> {
}
