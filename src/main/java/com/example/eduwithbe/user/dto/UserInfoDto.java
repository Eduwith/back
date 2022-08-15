package com.example.eduwithbe.user.dto;

import com.example.eduwithbe.user.domain.UserEntity;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {

    private String email;
    private String name;
    private int age;
    private char gender;
    private String address;

    public UserInfoDto(UserEntity user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.address = user.getAddress();
    }
}
