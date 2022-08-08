package com.example.eduwithbe.dto;

import com.example.eduwithbe.domain.MentoringApplyEntity;
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
    private Long m_no;
    private String email;

    public MentoringApplyEntity toEntity(){
        return MentoringApplyEntity.builder()
                .m_no(m_no)
                .email(email)
                .build();
    }
}
