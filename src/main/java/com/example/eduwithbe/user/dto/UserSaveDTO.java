package com.example.eduwithbe.user.dto;

import com.example.eduwithbe.user.domain.UserEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveDTO {

    private String email;
    private String name;
    private String pwd;
    private int age;
    private char gender;
    private String address;

    public UserSaveDTO(UserEntity user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.pwd = user.getPwd();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.address = user.getAddress();
    }
}
