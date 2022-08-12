package com.example.eduwithbe.controller;

import com.example.eduwithbe.Service.StudyService;
import com.example.eduwithbe.Service.UserService;
import com.example.eduwithbe.domain.UserEntity;
import com.example.eduwithbe.dto.StudyRegisterRequestDto;
import com.example.eduwithbe.dto.StudyResponseDto;
import com.example.eduwithbe.paging.CommonParams;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/studies")
public class StudyController {

    public final StudyService studyService;
    public final UserService userService;

    // 모든 스터디 모집글 조회 (With. Pagination)
    @GetMapping("")
    public Map<String, Object> getAllStudies(final CommonParams params) {
        System.out.println(params); // 페이지 정보 출력
        return studyService.getAllStudies(params);
    }

    // 스터디 모집글 상세정보 조회
    @GetMapping("/{stdNo}")
    public StudyResponseDto getStudyByNo(@PathVariable final Long stdNo) {
        return studyService.getStudyByNo(stdNo);
    }

    // todo 스터디 신청하기 (로그인한 사용자 정보 가져오는 방법?)
//    @PostMapping("/{stdNo}")
//    public Long applyStudy(@PathVariable Long stdNo, Principal principal) {
//        UserEntity user = userService.getUserFromAuth();
//        System.out.println("********* user : " + user.getEmail());
//        System.out.println("********* principal : " + principal.getName());
//        return studyService.applyStudy(stdNo, user.getEmail());
//    }

    // 스터디 태그 검색
    @GetMapping("/search")
    public Map<String, Object> searchStudyByTag(final CommonParams params) {
        System.out.println(params.getKeyword()); // 키워드 출력해보기
        return studyService.getAllStudies(params);
    }

   // 모집글 등록 화면으로 이동
    @GetMapping("/register")
    public void registerForm() {

    }

    // 스터디 모집글 등록 후 목록 화면으로 이동
    @PostMapping("/register")
    public void registerStudy(@RequestBody StudyRegisterRequestDto studyReq, HttpServletResponse response) throws IOException {
        // 스터디 모집글 등록
        // todo Dto에서 user의 이메일만 받는 방법?
        Long registerNo = studyService.registerStudy(studyReq);
        System.out.println("스터디 등록 번호" + registerNo);

        //목록 화면으로 리다이렉트
        String redirect_url = "http://localhost:8080/api/studies?page=1&pageSize=10";
        response.sendRedirect(redirect_url);
    }


   // todo 스터디 모집글 수정 (추후 마이페이지로 옮김)
    @PutMapping("/{stdNo}")
    public Long updateStudy(@PathVariable final Long stdNo, @RequestBody StudyRegisterRequestDto studyReq) {
        return studyService.updateStudy(stdNo, studyReq);
    }

    // 스터디 모집글 삭제
    @DeleteMapping("/{stdNo}")
    public void deleteStudy(@PathVariable Long stdNo) {
        studyService.deleteStudy(stdNo);
    }


}
