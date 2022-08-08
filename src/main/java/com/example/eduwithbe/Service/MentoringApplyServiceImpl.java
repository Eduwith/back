package com.example.eduwithbe.Service;

import com.example.eduwithbe.domain.MentoringApplyEntity;
import com.example.eduwithbe.dto.MentoringApplySaveDto;
import com.example.eduwithbe.repository.MentoringApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MentoringApplyServiceImpl implements MentoringApplyService{

    @Autowired
    private final MentoringApplyRepository mr;

    //멘토링 신청
    @Override
    public String saveMentoringApply(MentoringApplySaveDto dto) {
        MentoringApplyEntity mentoringApply = mr.save(dto.toEntity());
        return "OK";
    }
}
