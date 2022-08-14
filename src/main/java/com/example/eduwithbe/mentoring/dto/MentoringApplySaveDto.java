package com.example.eduwithbe.mentoring.dto;

import com.example.eduwithbe.mentoring.domain.MentoringApplyEntity;
import com.example.eduwithbe.mentoring.domain.MentoringRecruitmentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoringApplySaveDto {
    private MentoringRecruitmentEntity m_no;
    private String email;
    private String name;
    private String result;

    public MentoringApplyEntity toEntity(){
        return MentoringApplyEntity.builder()
                .m_no(m_no)
                .email(email)
                .name(name)
                .result("0")
                .build();
    }
}
