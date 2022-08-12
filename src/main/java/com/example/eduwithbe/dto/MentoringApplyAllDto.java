package com.example.eduwithbe.dto;

import com.example.eduwithbe.domain.MentoringApplyEntity;
import com.example.eduwithbe.domain.MentoringRecruitmentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoringApplyAllDto {
    private Long apply_no;
    private Long m_no;
    private String email;
    private String result;

    public MentoringApplyAllDto(MentoringApplyEntity mentoringApply) {
        this.apply_no = mentoringApply.getApply_no();
        this.m_no = mentoringApply.getM_no().getM_no();
        this.email = mentoringApply.getEmail();
        this.result = mentoringApply.getResult();
    }
}
