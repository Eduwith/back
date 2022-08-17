package com.example.eduwithbe.user.dto;

import com.example.eduwithbe.user.domain.UserAttendanceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserGetAttendDto {

    private int stamp;
    private int point;
    private int day;
    private List<UserAttendanceEntity> userAttendance;

}
