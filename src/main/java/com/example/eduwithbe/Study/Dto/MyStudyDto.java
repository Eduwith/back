package com.example.eduwithbe.Study.Dto;

import com.example.eduwithbe.Study.Domain.StudyRecruitmentEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyStudyDto {
    private StudyRecruitmentEntity study;   // 내가 등록한 스터디 모집글
    private List<StudyApplyInfoDto> applies;    // 신청 현황 리스트

    @Builder
    public MyStudyDto(StudyRecruitmentEntity study, List<StudyApplyInfoDto> applies) {
        this.study = study;
        this.applies = applies;
    }
}
