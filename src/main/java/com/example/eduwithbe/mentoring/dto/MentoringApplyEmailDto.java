package com.example.eduwithbe.mentoring.dto;

import com.example.eduwithbe.mentoring.domain.MentoringApplyEntity;
import com.example.eduwithbe.mentoring.domain.MentoringRecruitmentEntity;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoringApplyEmailDto {

    private Long apply_no;
    private Long m_no;
    private String email;

    public MentoringApplyEmailDto(MentoringApplyEntity ma) {
        this.apply_no = ma.getApply_no();
        this.m_no = ma.getM_no().getM_no();
        this.email = ma.getEmail();
    }
}
