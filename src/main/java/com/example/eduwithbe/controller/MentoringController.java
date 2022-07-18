package com.example.eduwithbe.controller;

import com.example.eduwithbe.Service.MentoringService;
import com.example.eduwithbe.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.dto.MentoringRecruitSaveDto;
import com.example.eduwithbe.dto.UserLoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/mentoring")
@RequiredArgsConstructor
@RestController
public class MentoringController {

    private final MentoringService boardService;

    //멘토링 작성 글 저장
    @PostMapping(value="")
    public String saveBoard(@RequestBody MentoringRecruitSaveDto saveBoardDto){
        System.out.println("=========="+saveBoardDto.getField());
        return boardService.saveMentoringRecruit(saveBoardDto);
    }

    //멘토링 작성 글 하나 찾기
    @GetMapping(value="/{boardId}")
    public String findOneBoard(@PathVariable Long boardId){
        MentoringRecruitmentEntity board = boardService.findByBoardId(boardId);
        return board.toString();
    }

    //멘토링 작성 글 전체 찾기
    @GetMapping(value="/list")
    public List<MentoringRecruitmentEntity> findAllBoard(){
        return boardService.findAllBoard();
    }

    //멘토링 작성 글 수정
    @PatchMapping(value="/{boardId}")
    public String updateBoard(@PathVariable Long boardId, @RequestBody MentoringRecruitSaveDto saveBoardDto){
        MentoringRecruitmentEntity board = boardService.findByBoardId(boardId);
        MentoringRecruitmentEntity updatedBoard = boardService.updateBoard(board, saveBoardDto);
        return updatedBoard.toString();
    }

    //멘토링 작성 글 삭제
    @DeleteMapping(value="/{boardId}")
    public String deleteBoard(@PathVariable Long boardId){
        MentoringRecruitmentEntity board = boardService.findByBoardId(boardId);
        boardService.deleteBoard(board);

        return "success delete";
    }
}
