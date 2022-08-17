package com.example.eduwithbe.mentoring.service;

import com.example.eduwithbe.mentoring.domain.MentoringEntity;
import com.example.eduwithbe.mentoring.domain.MentoringLogEntity;
import com.example.eduwithbe.mentoring.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.mentoring.dto.*;
import com.example.eduwithbe.mentoring.repository.MentoringLogRepository;
import com.example.eduwithbe.user.domain.UserEntity;
import com.example.eduwithbe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MentoringLogServiceImpl implements MentoringLogService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final MentoringLogRepository mentoringLogRepository;

    //일지 저장
    @Override
    public void saveMentoringLog(String user, MentoringEntity mentoring, MentoringLogSaveDto dto){
        //UserEntity userEntity = userRepository.findByEmail(user).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다." + user));
        MentoringLogSaveMentoringDto mentoringLog = new MentoringLogSaveMentoringDto();
        mentoringLog.setContent(dto.getContent());
        mentoringLog.setDate(CurrentDateTime());
        mentoringLog.setTitle(dto.getTitle());
        mentoringLog.setMentoring_no(mentoring);

        MentoringLogEntity mentoringLogEntity = mentoringLog.toEntity();
        mentoringLogRepository.save(mentoringLogEntity);
    }

    //일지 상세정보
    @Override
    public MentoringLogEntity findByMentoringLogId(Long log_no){
        return mentoringLogRepository.findById(log_no).orElseThrow();
    }

    //일지 전체 리스트
    @Override
    public List<MentoringLogGetIdDto> findAllMentoringLog(Long mentoring_no){
        List<MentoringLogEntity> mentoringLog = mentoringLogRepository.findByMentoringIdLog(mentoring_no);
        return mentoringLog.stream()
                .map(MentoringLogGetIdDto::new)
                .collect(Collectors.toList());
    }

    //일지 수정
    @Override
    public void updateMentoringLog(Long log_no, MentoringLogUpdateDto updateDto) {
        mentoringLogRepository.updateByMentoringLog(log_no, updateDto.getTitle(), updateDto.getContent());
    }

    //일지 삭제
    @Override
    public void deleteMentoringLog(MentoringLogEntity mentoringLogEntity){
        mentoringLogRepository.delete(mentoringLogEntity);
    }

    public Date CurrentDateTime() { //현재 날짜 구하기
        LocalDate now = LocalDate.now();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        // return now.format(formatter);
        return Date.valueOf(LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth()));
    }
}
