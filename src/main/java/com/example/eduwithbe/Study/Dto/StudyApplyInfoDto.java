package com.example.eduwithbe.Study.Dto;

import com.example.eduwithbe.Study.Domain.StudyApplyEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyApplyInfoDto {
    private Long s_no;
    private StudyApplyEntity apply;
    private UserDetailDto userDetail;

    @Builder
    public StudyApplyInfoDto(Long s_no, StudyApplyEntity apply, UserDetailDto userDetail) {
        this.s_no = s_no;
        this.apply = apply;
        this.userDetail = userDetail;
    }
}
