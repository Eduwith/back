package com.example.eduwithbe.user.repository;

import com.example.eduwithbe.mentoring.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.user.domain.UserAttendanceEntity;
import com.example.eduwithbe.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserAttendRepository extends JpaRepository<UserAttendanceEntity, Long> {
    @Query("select m from UserAttendanceEntity m where m.user.email = :email")
    List<UserAttendanceEntity> findByEmailAttendance(@Param("email") String email);
}
