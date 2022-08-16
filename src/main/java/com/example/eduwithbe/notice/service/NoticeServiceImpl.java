package com.example.eduwithbe.notice.service;

import com.example.eduwithbe.mentoring.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.mentoring.dto.MentoringRecruitSaveDto;
import com.example.eduwithbe.mentoring.dto.MentoringRecruitSaveUserDto;
import com.example.eduwithbe.mentoring.repository.MentoringRecruitmentRepository;
import com.example.eduwithbe.notice.domain.NoticeEntity;
import com.example.eduwithbe.notice.dto.NoticeSaveDto;
import com.example.eduwithbe.notice.repository.NoticeRepository;
import com.example.eduwithbe.user.domain.UserEntity;
import com.example.eduwithbe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private final NoticeRepository noticeRepository;

    @Autowired
    private final UserRepository userRepository;

    //멘토링 작성 글 생성 Mentoring(멘토링신청), Study(스터디), Message(쪽지), 마감날짜
    public NoticeEntity saveNotice(NoticeSaveDto dto) {
        NoticeSaveDto noticeSaveDto = new NoticeSaveDto();
        noticeSaveDto.setUser(dto.getUser());
        noticeSaveDto.setTitle(dto.getTitle());
        noticeSaveDto.setDate(CurrentDateTime());
        noticeSaveDto.setField(dto.getField());

        NoticeEntity noticeEntity = noticeSaveDto.toEntity();
        noticeRepository.save(noticeEntity);
        return noticeEntity;
    }

    public Date CurrentDateTime() { //현재 날짜 구하기
        LocalDate now = LocalDate.now();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        // return now.format(formatter);
        return Date.valueOf(LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth()));
    }
}
