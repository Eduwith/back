package com.example.eduwithbe.Service;

import com.example.eduwithbe.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.domain.UserEntity;
import com.example.eduwithbe.dto.MentoringRecruitSaveDto;
import com.example.eduwithbe.dto.MentoringRecruitSearch;
import com.example.eduwithbe.repository.MentoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MentoringService {

    Long saveMentoringRecruit(MentoringRecruitSaveDto dto);

    List<MentoringRecruitmentEntity> findByTitleContaining(String keyword);

    MentoringRecruitmentEntity findByBoardId(Long boardId);

    List<MentoringRecruitmentEntity> findAllBoard();

    MentoringRecruitmentEntity updateBoard(MentoringRecruitmentEntity board, MentoringRecruitSaveDto saveBoardDto);

    void deleteBoard(MentoringRecruitmentEntity board);

    List<MentoringRecruitSearch> findByFilter(List<String> field, List<String> region, List<Integer> m_period, List<String> way);
}
