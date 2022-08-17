package com.example.eduwithbe.mentoring.domain;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_no")
    private MentoringRecruitmentEntity m_no;

    private String email;

    private String name;

    private int age;
}
