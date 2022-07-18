package com.example.eduwithbe.Service;

import com.example.eduwithbe.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.domain.UserEntity;
import com.example.eduwithbe.dto.MentoringRecruitSaveDto;
import com.example.eduwithbe.repository.MentoringRepository;
import com.example.eduwithbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentoringServiceImpl implements MentoringService {

    @Autowired
    private final UserService userService;

    @Autowired
    private final MentoringRepository mr;

    //멘토링 작성 글 생성
    public String saveMentoringRecruit(MentoringRecruitSaveDto dto){
        //UserEntity userEntity = userService.getUserFromAuth();
        //dto.setUser(userEntity);
        MentoringRecruitmentEntity board = mr.save(dto.toEntity());
        return board.getEmail();
    }

    //멘토링 작성 글 하나 가져옴
    public MentoringRecruitmentEntity findByBoardId(Long boardId){
        MentoringRecruitmentEntity board = mr.findById(boardId).orElseThrow();
        return board;
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
}
