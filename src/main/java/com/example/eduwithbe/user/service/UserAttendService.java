package com.example.eduwithbe.user.service;

import com.example.eduwithbe.mentoring.domain.MentoringApplyEntity;
import com.example.eduwithbe.mentoring.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.mentoring.dto.MentoringApplySaveDto;
import com.example.eduwithbe.user.domain.UserAttendanceEntity;
import com.example.eduwithbe.user.domain.UserEntity;
import com.example.eduwithbe.user.dto.UserSaveAttendDto;
import com.example.eduwithbe.user.repository.UserAttendRepository;
import com.example.eduwithbe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class UserAttendService {

    @Autowired
    private final UserAttendRepository userAttendRepository;
    //출석체크 내용 저장
    public String saveUserAttendApply(UserEntity user, String title, int point) {

        UserSaveAttendDto userSaveAttendDto = new UserSaveAttendDto();

        userSaveAttendDto.setUser(user);
        userSaveAttendDto.setTitle(title);
        userSaveAttendDto.setDate(CurrentDateTime());
        userSaveAttendDto.setPoint(point);

        UserAttendanceEntity userAttendance = userSaveAttendDto.toEntity();
        userAttendRepository.save(userAttendance);

        //MentoringApplyEntity mentoringApply = mr.save(dto.toEntity());
        return "OK";
    }

    public Date CurrentDateTime() { //현재 날짜 구하기
        LocalDate now = LocalDate.now();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        // return now.format(formatter);
        return Date.valueOf(LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth()));
    }
}
