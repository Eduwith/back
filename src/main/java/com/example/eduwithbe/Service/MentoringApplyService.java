package com.example.eduwithbe.Service;

import com.example.eduwithbe.dto.MentoringApplyAllDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MentoringApplyService {
    String saveMentoringApply(String email, Long m_no);

    ResponseEntity<MentoringApplyAllDto> retrieveAllComment(Long m_no, String header);
}
