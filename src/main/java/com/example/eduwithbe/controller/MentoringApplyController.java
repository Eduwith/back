package com.example.eduwithbe.controller;


import com.example.eduwithbe.Service.MentoringApplyService;
import com.example.eduwithbe.Service.MentoringRecruitmentService;
import com.example.eduwithbe.dto.MentoringApplySaveDto;
import com.example.eduwithbe.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/mentoring")
@RequiredArgsConstructor
@RestController
public class MentoringApplyController {

    private final MentoringApplyService mentoringApplyService;
    private final JwtTokenProvider jwtTokenProvider;

    //멘토링 멘티/멘토 지원
    @PostMapping(value = "/{m_no}/apply")
    public Map<String, String> saveBoard(HttpServletRequest request, @PathVariable Long m_no) {
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));

        Map<String, String> map = new HashMap<>();
        map.put("result", "SUCCESS");

        mentoringApplyService.saveMentoringApply(user, m_no);

        return map;
    }
}
