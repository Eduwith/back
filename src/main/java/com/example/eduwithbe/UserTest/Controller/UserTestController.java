package com.example.eduwithbe.UserTest.Controller;

import com.example.eduwithbe.UserTest.Service.UserTestService;
import com.example.eduwithbe.UserTest.DTO.UserTestSaveDto;
import com.example.eduwithbe.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/userTest")
public class UserTestController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserTestService userTestService;

    // 학습 유형 테스트 실시
    @GetMapping("")
    public Map<String, String> studyTypeTest(HttpServletRequest request, @RequestBody UserTestSaveDto userTestSaveDto) {
        // 현재 로그인한 사용자 이메일 추출
        String userEmail = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));

        // 테스트 결과 저장
        String response = userTestService.saveTestResult(userEmail, userTestSaveDto);

        Map<String, String> result = new HashMap<>();
        result.put("studyTypeTest result", response);

        return result;
    }

}
