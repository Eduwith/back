package com.example.eduwithbe.user.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자 생성
public class UserSaveDTO {

    private String email;
    private String name;
    private String pwd;
    private int age;
    private char gender;
    private String address;
}
