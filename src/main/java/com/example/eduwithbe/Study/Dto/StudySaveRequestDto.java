package com.example.eduwithbe.Study.Dto;

import com.example.eduwithbe.Study.Domain.StudyRecruitmentEntity;
import com.example.eduwithbe.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudySaveRequestDto {
    private String title; // 제목
    private String contents; // 내용
    private String tag; // 태그
    private int total_people; // 모집 인원
    private Date r_end_date; // 모집 마감 기한
    private int s_period; // 스터디 기간

    // Dto -> Entity
    public StudyRecruitmentEntity toEntity(UserEntity writer) {
        return StudyRecruitmentEntity.builder()
                .title(title)
                .contents(contents)
                .tag(tag)
                .total_people(total_people)
                .current_people(0)
                .r_end_date(r_end_date)
                .s_period(s_period)
                .user(writer)
                .recruitYN('N')
                .build();
    }
}
