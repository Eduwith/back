package com.example.eduwithbe.user.dto;

import com.example.eduwithbe.user.domain.UserAttendanceEntity;
import com.example.eduwithbe.user.domain.UserEntity;
import lombok.*;

import java.sql.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveAttendDto {
    private String title;
    private Date date;
    private int point;
    private UserEntity user;

    public UserAttendanceEntity toEntity(){
        return UserAttendanceEntity.builder()
                .title(title)
                .date(date)
                .point(point)
                .user(user)
                .build();
    }
}
