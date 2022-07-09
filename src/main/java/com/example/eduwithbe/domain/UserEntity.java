package com.example.eduwithbe.domain;

import com.example.eduwithbe.dto.UserSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity // JPA를 사용하려면 Entity Class가 팔수적이다.
@Getter
@Setter
@Table(name="user")
public class UserEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment와 같은 역할
    @Column(name = "email")
    private String email;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 50)
    private String pwd;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private char gender;

    @Column(name = "address")
    private String address;

    // MemberSaveDTO -> MemberEntity 객체로 변환하기 위한 메서드
    public static UserEntity saveUser(UserSaveDTO userSaveDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(userSaveDTO.getEmail());
        userEntity.setName(userSaveDTO.getName());
        userEntity.setPwd(userSaveDTO.getPwd());
        userEntity.setAge(userSaveDTO.getAge());
        userEntity.setGender(userSaveDTO.getGender());
        userEntity.setAddress(userSaveDTO.getAddress());

        return userEntity;
    }

}