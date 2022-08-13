package com.example.eduwithbe.mentoring.repository;

import com.example.eduwithbe.mentoring.domain.MentoringApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentoringApplyRepository extends JpaRepository<MentoringApplyEntity, Long> {
    @Query("select m from MentoringApplyEntity m where m.email = :email")
    List<MentoringApplyEntity> findByEmail(@Param("email") String email);
}
