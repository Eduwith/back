package com.example.eduwithbe.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class MentoringMentorMenteeDto {
    private List<MentoringRecruitSearchDto> mentor;
    private List<MentoringRecruitSearchDto> mentee;

    @Builder
    public MentoringMentorMenteeDto(List<MentoringRecruitSearchDto> mentor, List<MentoringRecruitSearchDto> mentee) {
        this.mentor = mentor;
        this.mentee = mentee;
    }
}
