package com.example.eduwithbe.Study.Dto;

import com.example.eduwithbe.Study.Domain.StudyRecruitmentEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StudyRecruitDto {
    private Long s_no; // 스터디 글 번호
    private String title; // 제목
    private String contents; // 내용
    private String tag; // 태그
    private int total_people; // 모집 인원
    private int current_people; // 현재 인원(수락 확정)
    private Date r_end_date; // 모집 마감 기한
    private int s_period; // 스터디 기간
    private char recruitYN; // 모집 완료 여부

    // todo 생각해볼 것 : 작성자의 이름 정도는 나와야 하지 않을까?
//    private String name;


    // Entity -> Dto
    public StudyRecruitDto(StudyRecruitmentEntity entity) {
        this.s_no = entity.getS_no();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.tag = entity.getTag();
        this.total_people = entity.getTotal_people();
        this.current_people = entity.getCurrent_people();
        this.r_end_date = entity.getR_end_date();
        this.s_period = entity.getS_period();
        this.recruitYN = entity.getRecruitYN();
    }

}
