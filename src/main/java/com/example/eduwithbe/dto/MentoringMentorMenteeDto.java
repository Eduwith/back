package com.example.eduwithbe.dto;

import com.example.eduwithbe.domain.MentoringRecruitmentEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class MentoringMentorMenteeDto {
    private List<MentoringRecruitSearch> mentor;
    private List<MentoringRecruitSearch> mentee;

    @Builder
    public MentoringMentorMenteeDto(List<MentoringRecruitSearch> mentor, List<MentoringRecruitSearch> mentee) {
        this.mentor = mentor;
        this.mentee = mentee;
    }
}
