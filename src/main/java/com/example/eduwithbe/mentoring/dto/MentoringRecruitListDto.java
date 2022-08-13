package com.example.eduwithbe.mentoring.dto;


import com.example.eduwithbe.mentoring.domain.MentoringRecruitmentEntity;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoringRecruitListDto {
    private Long m_no;
    private String title;
    private String role;
    private String field;
    private String region;
    private int m_period;
    private String way;
    private String keyword;
    private String info;
    private String name;

    //private UserEntity user;
    //private String email;

    @Builder
    public MentoringRecruitListDto(MentoringRecruitmentEntity me) {
        this.m_no = me.getM_no();
        this.title = me.getTitle();
        this.role = me.getRole();
        this.field = me.getField();
        this.region = me.getRegion();
        this.m_period = me.getM_period();
        this.way = me.getWay();
        this.keyword = me.getKeyword();
        this.info = me.getInfo();
        this.name = me.getName();
    }
}
