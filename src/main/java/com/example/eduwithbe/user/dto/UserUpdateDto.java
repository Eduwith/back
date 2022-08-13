package com.example.eduwithbe.user.dto;

import com.example.eduwithbe.mentoring.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    private String name;
    private String pwd;
    private String address;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .name(name)
                .pwd(pwd)
                .address(address)
                .build();
    }
}
