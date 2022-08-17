package com.example.eduwithbe.mentoring.service;

import com.example.eduwithbe.mentoring.domain.MentoringEntity;
import com.example.eduwithbe.mentoring.domain.MentoringLogEntity;
import com.example.eduwithbe.mentoring.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.mentoring.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MentoringLogService {
    void saveMentoringLog(String user, MentoringEntity mentoring, MentoringLogSaveDto dto);

    MentoringLogEntity findByMentoringLogId(Long log_no);

    List<MentoringLogGetIdDto> findAllMentoringLog(Long mentoring_no);

    void updateMentoringLog(Long log_no, MentoringLogUpdateDto updateDto);

    void deleteMentoringLog(MentoringLogEntity mentoringLogEntity);
}
