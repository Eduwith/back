package com.example.eduwithbe.mentoring.dto;

import com.example.eduwithbe.mentoring.domain.MentoringApplyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoringApplyAllDto {
    private Long apply_no;
    private Long m_no;
    private String email;
    private String name;
    private String result;

    public MentoringApplyAllDto(MentoringApplyEntity mentoringApply) {
        this.apply_no = mentoringApply.getApply_no();
        this.m_no = mentoringApply.getM_no().getM_no();
        this.email = mentoringApply.getEmail();
        this.name = mentoringApply.getName();
        this.result = mentoringApply.getResult();
    }
}
