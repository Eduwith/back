package com.example.eduwithbe.user.dto;

import com.example.eduwithbe.user.domain.UserEntity;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserMentoringApplyDetailDTO {
    private String email;
    private String name;
    private int age;

    @Builder
    public UserMentoringApplyDetailDTO(String email, String name, int age) {
        this.email = email;
        this.name = name;
        this.age = age;
    }
}
