package com.example.eduwithbe.domain;

import com.example.eduwithbe.dto.UserSaveDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "usertest7")
public class UserEntity implements UserDetails {


    //    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment와 같은 역할
//    @Column(name = "id", unique = true)
//    private Long id;
    @Id
    @Column(name = "email", unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private char gender;

    @Column(name = "address")
    private String address;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    public List<MentoringRecruitmentEntity> mentoringRecruitments = new ArrayList<>();

//    public void newMentoringRecruitment(MentoringRecruitmentEntity m){
//        this.mentoringRecruitments.
//    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         // TODO Auto-generated method stub

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }


//    @ElementCollection(fetch = FetchType.EAGER)
//    @Builder.Default
//    private List<String> roles = new ArrayList<>();
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }

    @Override
    public String getPassword() {
        return pwd;
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public enum Authority {
        ROLE_USER, ROLE_ADMIN
    }

}
