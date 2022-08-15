package com.example.eduwithbe.mentoring.repository;

import com.example.eduwithbe.mentoring.domain.MentoringApplyEntity;
import com.example.eduwithbe.mentoring.domain.MentoringScrapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentoringRecruitScrapRepositoty extends JpaRepository<MentoringScrapEntity, Long> {

}
