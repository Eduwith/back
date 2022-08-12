package com.example.eduwithbe.repository;

import com.example.eduwithbe.domain.MentoringApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentoringApplyRepository extends JpaRepository<MentoringApplyEntity, Long> {
}
