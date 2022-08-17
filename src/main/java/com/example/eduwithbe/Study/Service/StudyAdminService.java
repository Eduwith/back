package com.example.eduwithbe.Study.Service;

import com.example.eduwithbe.Study.Domain.StudyApplyEntity;
import com.example.eduwithbe.Study.Domain.StudyRecruitmentEntity;
import com.example.eduwithbe.Study.Domain.StudyingEntity;
import com.example.eduwithbe.Study.Dto.MyStudyDto;
import com.example.eduwithbe.Study.Dto.StudyApplyInfoDto;
import com.example.eduwithbe.Study.Dto.UserDetailDto;

import java.util.List;
import java.util.Map;

public interface StudyAdminService {
    List<StudyRecruitmentEntity> findMyStudyRecruits(String myEmail);    // 내가 작성한 스터디 모집글 확인하기

    List<StudyApplyEntity> findStudyApplyOfMyStudy(Long s_no); // 스터디 신청 현황 조회

    List<MyStudyDto> findMyStudies(String myEmail); // 내가 등록한 스터디 모집글 & 신청 현황 조회

    UserDetailDto findUserDetail(Long apply_no); // 내 스터디 신청자 확인

    String saveStudying(Long stdNo, Long apply_no); // 신청자 수락

    String deleteStudyApply(Long apply_no); // 신청자 거절

    String finishStudy(final Long stdNo, char recruitYN);   // 스터디 모집 마감하기

    List<StudyingEntity> findMyStudying(String myEmail); // 나의 매칭된 스터디 내역
}
