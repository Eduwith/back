package com.example.eduwithbe.dto;

import com.example.eduwithbe.domain.StudyRecruitment;
import com.example.eduwithbe.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyRegisterRequestDto {
    private String title; // 제목
    private String contents; // 내용
    private String tag; // 태그
    private int total_people; // 모집 인원
    private Date r_end_date; // 모집 마감 기한
    private int s_period; // 스터디 기간
    private UserEntity user;   // 작성자

//    @Builder
//    public StudyRegisterRequestDto(String title, String contents, String tag, int total_people,
//                                   Date r_end_date, int s_period, UserEntity user) {
//        this.title = title;
//        this.contents = contents;
//        this.tag = tag;
//        this.total_people = total_people;
//        this.r_end_date = r_end_date;
//        this.s_period = s_period;
//        this.user = user;
//    }

    // Dto -> Entity
    public StudyRecruitment toEntity() {
        return StudyRecruitment.builder()
                .title(title)
                .contents(contents)
                .tag(tag)
                .total_people(total_people)
                .current_people(0)
                .r_end_date(r_end_date)
                .s_period(s_period)
                .user(user)
                .build();
    }
}
