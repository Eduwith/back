package com.example.eduwithbe.mappers;

import com.example.eduwithbe.Study.Dto.StudyRecruitDto;
import com.example.eduwithbe.paging.CommonParams;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyMapper {
    // 모집글 수 조회
    int count(final CommonParams params);

    // 모집글 리스트 조회
    List<StudyRecruitDto> findAll(final CommonParams params);
}
