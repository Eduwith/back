package com.example.eduwithbe.Study.Domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "study_scrap")
@Entity
public class StudyScrapEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrapNo;   // 스크랩 번호

    @Column(nullable = false)
    private String email;   // 사용자 이메일

    @Column(nullable = false)
    private Long s_no;  // 스터디글번호

    @Builder
    public StudyScrapEntity(String email, Long s_no) {
        this.email = email;
        this.s_no = s_no;
    }
}
