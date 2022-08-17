package com.example.eduwithbe.Study.Repository;

import com.example.eduwithbe.Study.Domain.StudyingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyingRepository extends JpaRepository<StudyingEntity, Long> {
    List<StudyingEntity> findByEmail(String email);
}
