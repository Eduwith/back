package com.example.eduwithbe.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="mentoringApply")
public class MentoringApplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_no")
    private Long apply_no;

    private Long m_no;

    private String email;

    @Column(nullable = false, length = 1) //수락Y 거부N
    private String result;
}
