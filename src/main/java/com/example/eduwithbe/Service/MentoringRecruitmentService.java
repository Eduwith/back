package com.example.eduwithbe.Service;

import com.example.eduwithbe.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.dto.MentoringRecruitListDto;
import com.example.eduwithbe.dto.MentoringRecruitSaveDto;
import com.example.eduwithbe.dto.MentoringRecruitSearchDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MentoringRecruitmentService {

    MentoringRecruitmentEntity saveMentoringRecruit(MentoringRecruitSaveDto dto);

    List<MentoringRecruitListDto> findByTitleContaining(String keyword);

    MentoringRecruitmentEntity findByMentoringRecruitId(Long m_no);

    List<MentoringRecruitListDto> findAllMentoringRecruitment();

    MentoringRecruitmentEntity updateBoard(MentoringRecruitmentEntity board, MentoringRecruitSaveDto saveBoardDto);

    void deleteBoard(MentoringRecruitmentEntity board);

    List<MentoringRecruitSearchDto> findByEmailMentoringMentor(String email);

    List<MentoringRecruitSearchDto> findByEmailMentoringMentee(String email);

    List<MentoringRecruitListDto> findByMentoringMentor();

    List<MentoringRecruitListDto> findByMentoringMentee();

    List<MentoringRecruitListDto> findByFilter(List<String> field, List<String> region, List<Integer> m_period, List<String> way);
}
