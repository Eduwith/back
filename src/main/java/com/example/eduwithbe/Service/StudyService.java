package com.example.eduwithbe.Service;

import com.example.eduwithbe.dto.StudyRegisterRequestDto;
import com.example.eduwithbe.dto.StudyResponseDto;
import com.example.eduwithbe.paging.CommonParams;

import java.util.Map;

public interface StudyService {
//    List<StudyResponseDto> getAllStudies(); // 스터디 모집글 전체 조회

    Map<String, Object> getAllStudies(CommonParams params);

    StudyResponseDto getStudyByNo(final Long stdNo); // 특정 스터디 모집글 조회

    void registerForm();    // 스터디 모집글 등록 페이지 이동

//    Long applyStudy(Long stdNo, String email); // 스터디 신청하기

    Long registerStudy(final StudyRegisterRequestDto studyRequestDto); // 스터디 모집글 등록

    Long updateStudy(final Long stdNo, final StudyRegisterRequestDto studyReq); // 스터디 모집글 수정

    void deleteStudy(Long stdNo); // 스터디 모집글 삭제
}
