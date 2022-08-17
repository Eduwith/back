package com.example.eduwithbe.Study.Dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetailDto {
    private String profile_img;
    private String name;
    private int age;
    private String email;

    @Builder
    public UserDetailDto(String profile_img, String name, int age, String email) {
        this.profile_img = profile_img;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
