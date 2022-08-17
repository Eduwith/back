package com.example.eduwithbe.mentoring.service;

import com.example.eduwithbe.mentoring.dto.MentoringApplyAllDto;
import com.example.eduwithbe.mentoring.dto.MentoringApplyEmailDto;
import com.example.eduwithbe.mentoring.dto.MentoringRecruitSearchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MentoringApplyService {
    String saveMentoringApply(String email, Long m_no);

    List<MentoringApplyEmailDto> findByEmail(String email);

    ResponseEntity<MentoringApplyAllDto> retrieveAllComment(Long m_no, String header);
}
