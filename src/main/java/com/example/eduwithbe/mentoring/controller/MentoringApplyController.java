package com.example.eduwithbe.mentoring.controller;


import com.example.eduwithbe.mentoring.dto.MentoringApplyEmailDto;
import com.example.eduwithbe.mentoring.service.MentoringApplyService;
import com.example.eduwithbe.mentoring.service.MentoringService;
import com.example.eduwithbe.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class MentoringApplyController {

    private final MentoringApplyService mentoringApplyService;
    private final MentoringService mentoringService;
    private final JwtTokenProvider jwtTokenProvider;

    //마이페이지 멘토링 신청 리스트
    @GetMapping("/mypage/apply")
    public List<MentoringApplyEmailDto> findByApplyEmail(HttpServletRequest request){
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));
        List<MentoringApplyEmailDto> list =  mentoringApplyService.findByEmail(user);
        System.out.println(list);
        return list;
    }

    //멘토링 멘티/멘토 지원
    @GetMapping(value = "/mentoring/{m_no}/apply")
    public Map<String, String> saveMentoringApply(HttpServletRequest request, @PathVariable Long m_no) {
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));

        Map<String, String> map = new HashMap<>();
        map.put("result", "SUCCESS");

        mentoringApplyService.saveMentoringApply(user, m_no);

        return map;
    }

    //멘토링 신청 수락
    @GetMapping(value = "/mypage/{m_no}/apply/{apply_no}")
    public Map<String, String> saveMentoringApplyAccept(HttpServletRequest request, @PathVariable Long m_no, @PathVariable Long apply_no) {
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));

        Map<String, String> map = new HashMap<>();
        map.put("result", "SUCCESS");

        mentoringService.saveMentoring(user, m_no, apply_no);

        return map;
    }

    //멘토링 신청 거절
    @DeleteMapping(value = "/mypage/{m_no}/apply/{apply_no}")
    public Map<String, String> saveMentoringApplyRefuse(HttpServletRequest request, @PathVariable Long m_no, @PathVariable Long apply_no) {
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));

        Map<String, String> map = new HashMap<>();
        map.put("result", "SUCCESS");

        mentoringService.deleteMentoring(apply_no);

        return map;
    }

    //멘토링 신청 취소
    @DeleteMapping(value = "/mypage/apoply/{apply_no}")
    public Map<String, String> saveMentoringApplyRefuse(HttpServletRequest request, @PathVariable Long apply_no) {
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));

        Map<String, String> map = new HashMap<>();
        map.put("result", "SUCCESS");

        mentoringService.deleteMentoring(apply_no);

        return map;
    }

}
