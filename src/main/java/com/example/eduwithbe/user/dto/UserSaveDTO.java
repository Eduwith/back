package com.example.eduwithbe.user.dto;

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
}
