package com.example.eduwithbe.user.domain;

import com.example.eduwithbe.domain.StudyRecruitment;
import com.example.eduwithbe.user.dto.UserSaveDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@Table(name = "user")
public class UserEntity implements UserDetails {

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


    //    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    public List<MentoringRecruitmentEntity> mentoringRecruitments = new ArrayList<>();

    // === 사용자-스터디 모집글 관계 설정 === //
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<StudyRecruitment> studyRecruitments = new ArrayList<>();

    // 모집글 작성
    public void addStudyRecruitment(StudyRecruitment study) {
        this.studyRecruitments.add(study);
    }

//    // === 사용자-스터디 지원 관계 설정 === //
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    public List<StudyApply> studyApplies = new ArrayList<>();
//
//    // 지원 정보 작성
//    public void addStudyApplies(StudyApply apply) {
//        this.studyApplies.add(apply);
//    }

//    public void newMentoringRecruitment(MentoringRecruitmentEntity m){
//        this.mentoringRecruitments.
//    }

    public static UserEntity saveUser(UserSaveDTO userSaveDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(userSaveDTO.getEmail());
        userEntity.setName(userSaveDTO.getName());
        userEntity.setPwd(userSaveDTO.getPwd());
        userEntity.setAge(userSaveDTO.getAge());
        userEntity.setGender(userSaveDTO.getGender());
        userEntity.setAddress(userSaveDTO.getAddress());
        userEntity.setRoles(Collections.singletonList("ROLE_USER"));

        return userEntity;
    }


    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

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

}
