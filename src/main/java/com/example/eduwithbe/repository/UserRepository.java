package com.example.eduwithbe.repository;

import com.example.eduwithbe.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByMemberEmail(String memberEmail);

}