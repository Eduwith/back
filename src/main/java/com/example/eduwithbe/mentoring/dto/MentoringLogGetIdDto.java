package com.example.eduwithbe.mentoring.dto;

import com.example.eduwithbe.mentoring.domain.MentoringLogEntity;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoringLogGetIdDto {
    private Long log_no;
    private String title;
    private Date date;
    private String content;

    @Builder
    public MentoringLogGetIdDto(MentoringLogEntity mentoringLogEntity) {
        this.log_no = mentoringLogEntity.getLog_no();
        this.title = mentoringLogEntity.getTitle();
        this.date = mentoringLogEntity.getDate();
        this.content = mentoringLogEntity.getContent();
    }
}
