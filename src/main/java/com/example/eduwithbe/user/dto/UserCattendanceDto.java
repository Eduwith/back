package com.example.eduwithbe.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCattendanceDto {
    private int stamp;
    private int point;
}
