package com.example.eduwithbe.dto;

import com.example.eduwithbe.domain.MentoringApplyEntity;
import com.example.eduwithbe.domain.MentoringRecruitmentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoringApplySaveDto {
    private MentoringRecruitmentEntity m_no;
    private String email;
    private String result;

    public MentoringApplyEntity toEntity(){
        return MentoringApplyEntity.builder()
                .m_no(m_no)
                .email(email)
                .result("0")
                .build();
    }
}
