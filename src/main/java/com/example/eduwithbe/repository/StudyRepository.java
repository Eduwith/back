package com.example.eduwithbe.repository;

import com.example.eduwithbe.domain.StudyRecruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudyRepository extends JpaRepository<StudyRecruitment, Long> {
//    Optional<StudyRecruitment> findByNo(Long stdNo);
}
