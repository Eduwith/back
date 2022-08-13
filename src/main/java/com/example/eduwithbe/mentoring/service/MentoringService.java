package com.example.eduwithbe.mentoring.service;

import com.example.eduwithbe.mentoring.domain.MentoringRecruitmentEntity;
import org.springframework.stereotype.Service;

@Service
public interface MentoringService {
    String saveMentoring(String email, Long m_no, Long apply_no);
    String deleteMentoring(Long apply_no);
}
