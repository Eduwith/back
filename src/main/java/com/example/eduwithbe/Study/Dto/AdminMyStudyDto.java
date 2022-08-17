package com.example.eduwithbe.Study.Dto;

import com.example.eduwithbe.Study.Domain.StudyingEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 내가 등록한 스터디 모집글 & 신청 현황 + 스터디 내역
public class AdminMyStudyDto {
    private List<MyStudyDto> myStudyDtoList;
    private List<StudyingEntity> myStudyingList;

    @Builder
    public AdminMyStudyDto(List<MyStudyDto> myStudyDtoList, List<StudyingEntity> myStudyingList) {
        this.myStudyDtoList = myStudyDtoList;
        this.myStudyingList = myStudyingList;
    }
}
