package com.example.eduwithbe.Service;

import com.example.eduwithbe.dto.MentoringApplySaveDto;
import org.springframework.stereotype.Service;

@Service
public interface MentoringApplyService {
    String saveMentoringApply(MentoringApplySaveDto dto);
}
