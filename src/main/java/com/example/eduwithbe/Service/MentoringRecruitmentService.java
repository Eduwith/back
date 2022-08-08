package com.example.eduwithbe.Service;

import com.example.eduwithbe.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.dto.MentoringApplySaveDto;
import com.example.eduwithbe.dto.MentoringRecruitSaveDto;
import com.example.eduwithbe.dto.MentoringRecruitSearch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MentoringRecruitmentService {

    Long saveMentoringRecruit(MentoringRecruitSaveDto dto);

    List<MentoringRecruitmentEntity> findByTitleContaining(String keyword);

    MentoringRecruitmentEntity findByMentoringRecruitId(Long m_no);

    List<MentoringRecruitmentEntity> findAllBoard();

    MentoringRecruitmentEntity updateBoard(MentoringRecruitmentEntity board, MentoringRecruitSaveDto saveBoardDto);

    void deleteBoard(MentoringRecruitmentEntity board);

    List<MentoringRecruitSearch> findByFilter(List<String> field, List<String> region, List<Integer> m_period, List<String> way);
}
