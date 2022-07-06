package com.example.eduwithbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
