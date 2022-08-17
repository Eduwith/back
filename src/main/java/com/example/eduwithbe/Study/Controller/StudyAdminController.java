package com.example.eduwithbe.Study.Controller;

import com.example.eduwithbe.Study.Dto.*;
import com.example.eduwithbe.Study.Service.StudyAdminService;
import com.example.eduwithbe.Study.Service.StudyService;
import com.example.eduwithbe.security.JwtTokenProvider;
import com.example.eduwithbe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class StudyAdminController{
    private final StudyAdminService studyAdminService;
    private final StudyService studyService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    // 내가 등록한 스터디 & 신청 현황 조회 + 매칭된 나의 스터디 내역
    @GetMapping("/study")
    public AdminMyStudyDto adminMyStudies(HttpServletRequest request) {
        // 사용자 이메일 추출
        String userEmail = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));

        return AdminMyStudyDto.builder()
                .myStudyDtoList(studyAdminService.findMyStudies(userEmail))
                .myStudyingList(studyAdminService.findMyStudying(userEmail))
                .build();
    }

    // 스터디 신청자 정보 확인
    @GetMapping ("/study/applicant/{applyNo}")
    public UserDetailDto findApplicantDetail(@PathVariable Long applyNo) {
        return studyAdminService.findUserDetail(applyNo);
    }

    // 신청자 수락
    @PostMapping("study/{stdNo}/apply/{applyNo}")
    public String saveStudyApplyAccept(HttpServletRequest request,
                                @PathVariable Long stdNo,
                                @PathVariable Long applyNo) {
//        String myEmail = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));
        return studyAdminService.saveStudying(stdNo, applyNo);
    }

    // 신청자 거절
    @DeleteMapping("study/{stdNo}/apply/{applyNo}")
    public String refuseStudyApply(@PathVariable Long stdNo,@PathVariable Long applyNo) {
        return studyAdminService.deleteStudyApply(applyNo);
    }


    // 스터디 모집글 수정 페이지로 (기존 내용 불러오기)
    @GetMapping("/study/update/{stdNo}")
    public StudyRecruitDto update(@PathVariable final Long stdNo) {
        return studyService.findStudyByNo(stdNo);
    }

    // 스터디 모집글 수정
    @PutMapping("/study/update/{stdNo}")
    public String updateStudyInfo(@PathVariable final Long stdNo, @RequestBody StudySaveRequestDto studyReq) {
        return studyService.updateStudy(stdNo, studyReq);
    }

    // 스터디 모집글 마감하기
    @PutMapping("/study/finish/{stdNo}")
    public String finishStudy(@PathVariable final Long stdNo, @RequestParam("recruitYN") char recruitYN) {
        return studyAdminService.finishStudy(stdNo, recruitYN);
    }
}
