package com.example.eduwithbe.Service;

import com.example.eduwithbe.domain.MentoringApplyEntity;
import com.example.eduwithbe.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.dto.MentoringApplySaveDto;
import com.example.eduwithbe.dto.MentoringMentorMenteeDto;
import com.example.eduwithbe.dto.MentoringRecruitSaveDto;
import com.example.eduwithbe.dto.MentoringRecruitSearch;
import com.example.eduwithbe.repository.MentoringRecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MentoringRecruitmentServiceImpl implements MentoringRecruitmentService {

    @Autowired
    private final UserService userService;

    @Autowired
    private final MentoringRecruitmentRepository mr;

    //멘토링 작성 글 생성
    public Long saveMentoringRecruit(MentoringRecruitSaveDto dto){
        //UserEntity userEntity = userService.getUserFromAuth();
        //dto.setUser(userEntity);

        MentoringRecruitmentEntity board = mr.save(dto.toEntity());
        return board.getM_no();
    }

    //멘토링 작성 글 하나 가져옴
    public MentoringRecruitmentEntity findByMentoringRecruitId(Long m_no){
        MentoringRecruitmentEntity mentoringRecruitment = mr.findById(m_no).orElseThrow();
        return mentoringRecruitment;
    }

    //멘토링 작성 글 전체 리스트
    public List<MentoringRecruitmentEntity> findAllBoard(){
        List<MentoringRecruitmentEntity> boards = mr.findAll();
        return boards;
    }

    //멘토링 작성 글 하나 수정
    public MentoringRecruitmentEntity updateBoard(MentoringRecruitmentEntity board, MentoringRecruitSaveDto saveBoardDto) {
        board.updateBoard(saveBoardDto);
        return board;
    }

    //멘토링 작성 글 하나 삭제
    public void deleteBoard(MentoringRecruitmentEntity board){
        mr.delete(board);
    }

    //키워드 검색
    public List<MentoringRecruitmentEntity> findByTitleContaining(String keyword) {
        return mr.findByTitleContaining(keyword);
    }

    //마이페이지 멘토링 글 목록 - 멘토
    public List<MentoringRecruitSearch> findByMentoringMentor(String email) {

        List<MentoringRecruitmentEntity> mentoringRecruitmentEntities = mr.findByMentoringMentor(email);

        return mentoringRecruitmentEntities.stream()
                .map(MentoringRecruitSearch::new)
                .collect(Collectors.toList());
    }

    //마이페이지 멘토링 글 목록 - 멘티
    public List<MentoringRecruitSearch> findByMentoringMentee(String email) {

        List<MentoringRecruitmentEntity> mentoringRecruitmentEntities = mr.findByMentoringMentee(email);

        return mentoringRecruitmentEntities.stream()
                .map(MentoringRecruitSearch::new)
                .collect(Collectors.toList());
    }

    //필터 검색
    public List<MentoringRecruitSearch> findByFilter(List<String> field, List<String> region, List<Integer> m_period, List<String> way) {

        List<MentoringRecruitmentEntity> mentoringRecruitmentEntities = mr.findByFilter(field, region, m_period, way);

        return mentoringRecruitmentEntities.stream()
                .map(MentoringRecruitSearch::new)
                .collect(Collectors.toList());
    }


}
