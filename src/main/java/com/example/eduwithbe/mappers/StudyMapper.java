package com.example.eduwithbe.mappers;

import com.example.eduwithbe.dto.StudyResponseDto;
import com.example.eduwithbe.paging.CommonParams;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyMapper {
    // 모집글 수 조회
    int count(final CommonParams params);

    // 모집글 리스트 조회
    List<StudyResponseDto> findAll(final CommonParams params);
}
