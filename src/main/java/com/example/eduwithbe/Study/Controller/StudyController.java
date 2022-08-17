package com.example.eduwithbe.Study.Controller;

import com.example.eduwithbe.Study.Dto.StudySaveRequestDto;
import com.example.eduwithbe.Study.Dto.StudyRecruitDto;
import com.example.eduwithbe.Study.Service.StudyService;
import com.example.eduwithbe.paging.CommonParams;
import com.example.eduwithbe.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/studies")
public class StudyController {

    private final StudyService studyService;

    private final JwtTokenProvider jwtTokenProvider;


    // 모든 스터디 모집글 조회 (With. Pagination)
    @GetMapping("")
    public Map<String, Object> getAllStudies(final CommonParams params) {
        return studyService.findAllStudies(params);
    }

    // 스터디 모집글 상세정보 조회
    @GetMapping("/{stdNo}")
    public StudyRecruitDto getStudyByNo(@PathVariable final Long stdNo) {
        return studyService.findStudyByNo(stdNo);
    }

    // 스터디 신청하기
    @PostMapping("/{stdNo}")
    public Map<String, String> applyStudy(HttpServletRequest request, @PathVariable Long stdNo) {
        // 로그인 사용자 이메일 추출
        String userEmail = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));

        // 서비스 실행 및 응답
        String response = studyService.saveStudyApply(userEmail, stdNo);

        Map<String, String> result = new HashMap<>();
        result.put("result", response);

        return result;
    }

    // 스터디 태그 검색
    @GetMapping("/search")
    public Map<String, Object> searchStudyByTag(final CommonParams params) {
        System.out.println(params.getKeyword()); // 키워드 출력해보기
        return studyService.findAllStudies(params);
    }

    // 스터디 모집글 등록 후 목록 화면으로 이동
    @PostMapping("/register")
    public void registerStudy(@RequestBody StudySaveRequestDto studyReq,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        // 로그인 한 사용자 이메일 추출
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));

        // 스터디 모집글 등록
        studyService.registerStudy(studyReq, user);

        //목록 화면으로 리다이렉트
        String redirect_url = "http://localhost:8080/api/studies?page=1&pageSize=10";
        response.sendRedirect(redirect_url);
    }

    // 스터디 모집글 삭제
    @DeleteMapping("/{stdNo}")
    public void deleteStudy(@PathVariable Long stdNo) {
        studyService.deleteStudy(stdNo);
    }
}
