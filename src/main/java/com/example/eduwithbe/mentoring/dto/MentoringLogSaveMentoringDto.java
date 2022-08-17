package com.example.eduwithbe.mentoring.dto;

import com.example.eduwithbe.mentoring.domain.MentoringEntity;
import com.example.eduwithbe.mentoring.domain.MentoringLogEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoringLogSaveMentoringDto {
    private MentoringEntity mentoring_no;
    private String title;
    private Date date;
    private String content;

    public MentoringLogEntity toEntity() {
        return MentoringLogEntity.builder()
                .mentoring_no(mentoring_no)
                .title(title)
                .date(date)
                .content(content)
                .build();
    }
}
