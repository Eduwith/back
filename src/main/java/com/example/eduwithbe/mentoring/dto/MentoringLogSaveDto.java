package com.example.eduwithbe.mentoring.dto;

import com.example.eduwithbe.mentoring.domain.MentoringEntity;
import com.example.eduwithbe.mentoring.domain.MentoringLogEntity;
import com.example.eduwithbe.mentoring.domain.MentoringRecruitmentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoringLogSaveDto {
    private Long mentoring_no;
    private String title;
    private String content;

    public MentoringLogEntity toEntity() {
        return MentoringLogEntity.builder()
                .title(title)
                .content(content)
                .build();
    }
}
