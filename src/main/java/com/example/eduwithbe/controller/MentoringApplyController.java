package com.example.eduwithbe.controller;


import com.example.eduwithbe.Service.MentoringApplyService;
import com.example.eduwithbe.Service.MentoringRecruitmentService;
import com.example.eduwithbe.dto.MentoringApplySaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/mentoring")
@RequiredArgsConstructor
@RestController
public class MentoringApplyController {

    private final MentoringApplyService mentoringApplyService;

    //멘토링 멘티/멘토 지원
    @PostMapping(value = "/apply")
    public String saveBoard(@RequestBody MentoringApplySaveDto saveBoardDto) {
        return mentoringApplyService.saveMentoringApply(saveBoardDto);
    }
}
