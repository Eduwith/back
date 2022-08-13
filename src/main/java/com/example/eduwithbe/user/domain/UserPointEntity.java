//package com.example.eduwithbe.user.domain;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Table(name = "userPoint")
//public class UserPointEntity {
//    @Id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "email")
//    private UserEntity email;
//
//    @Column(nullable = false, columnDefinition = "0")
//    private int stamp;
//
//    @Column(nullable = false, columnDefinition = "0")
//    private int point;
//}
