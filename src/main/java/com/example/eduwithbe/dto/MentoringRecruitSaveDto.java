package com.example.eduwithbe.dto;

import com.example.eduwithbe.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoringRecruitSaveDto {

    private String title;
    private String role;
    private String field;
    private String region;
    private String r_period;
    private String m_period;
    private String way;
    private String keyword;
    private String info;

    //private UserEntity user;
    private String email;

    public MentoringRecruitmentEntity toEntity(){
        return MentoringRecruitmentEntity.builder()
                .title(title)
                .role(role)
                .field(field)
                .region(region)
                .r_period(r_period)
                .m_period(m_period)
                .way(way)
                .keyword(keyword)
                .info(info)
                .email(email)
                .build();
    }
}
