package com.example.eduwithbe.Study.Repository;

import com.example.eduwithbe.Study.Domain.StudyScrapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyScrapRepository extends JpaRepository<StudyScrapEntity, Long> {
}
