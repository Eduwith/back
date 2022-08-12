package com.example.eduwithbe.Service;

import com.example.eduwithbe.domain.MentoringApplyEntity;
import com.example.eduwithbe.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.domain.UserEntity;
import com.example.eduwithbe.dto.MentoringApplyAllDto;
import com.example.eduwithbe.dto.MentoringApplySaveDto;
import com.example.eduwithbe.repository.MentoringApplyRepository;
import com.example.eduwithbe.repository.MentoringRecruitmentRepository;
import com.example.eduwithbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MentoringApplyServiceImpl implements MentoringApplyService {

    @Autowired
    private final MentoringApplyRepository mr;

    @Autowired
    private final UserRepository ur;

    @Autowired
    private final MentoringRecruitmentRepository mrr;

    //멘토링 신청
    @Override
    public String saveMentoringApply(String email, Long m_no) {
        Optional<UserEntity> user = ur.findByEmail(email);
        MentoringRecruitmentEntity mentoringRecruitment = mrr.findById(m_no).orElseThrow(() -> new IllegalArgumentException("신청 실패: 해당 멘토링이 존재하지 않습니다." + m_no));

        MentoringApplySaveDto mentoringApplySaveDto = new MentoringApplySaveDto();

        mentoringApplySaveDto.setEmail(user.get().getEmail());
        mentoringApplySaveDto.setM_no(mentoringRecruitment);

        MentoringApplyEntity apply = mentoringApplySaveDto.toEntity();
        mr.save(apply);

        //MentoringApplyEntity mentoringApply = mr.save(dto.toEntity());
        return "OK";
    }


    @Override
    public ResponseEntity<MentoringApplyAllDto> retrieveAllComment(Long m_no, String header) {

        return null;
    }
}
