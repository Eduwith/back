package com.example.eduwithbe.UserTest.DTO;

import com.example.eduwithbe.UserTest.Domain.UserTestEntity;
import lombok.Getter;

@Getter
public class UserTestSaveDto {
    private String mbti;    // 학습 MBTI
    private String animal;  // 동물

    public UserTestEntity toEntity(String userEmail) {
        return UserTestEntity.builder()
                .email(userEmail)
                .mbti(mbti)
                .animal(animal)
                .build();
    }

}
