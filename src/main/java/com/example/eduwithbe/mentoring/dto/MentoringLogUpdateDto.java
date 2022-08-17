package com.example.eduwithbe.mentoring.dto;

import com.example.eduwithbe.mentoring.domain.MentoringLogEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoringLogUpdateDto {
    private String title;
    private String content;

    public MentoringLogEntity toEntity() {
        return MentoringLogEntity.builder()
                .title(title)
                .content(content)
                .build();
    }
}
