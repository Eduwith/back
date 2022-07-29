package com.example.eduwithbe.domain;

import com.example.eduwithbe.dto.MentoringRecruitSaveDto;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="mentoringRecruitment")
public class MentoringRecruitmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_no")
    private Long m_no;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 1)
    private String role;

    @Column(nullable = false, length = 30)
    private String field;

    @Column(nullable = false, length = 50)
    private String region;

    @Column(nullable = false)
    private String r_period;

    @Column(nullable = false)
    private int m_period;

    @Column(nullable = false, length = 4)
    private String way;

    @Column(nullable = false, length = 50)
    private String keyword;

    @Column(nullable = false, length = 200)
    private String info;

    @ManyToOne
    @JoinColumn(name = "User_Email")
    private UserEntity user;


    private String email;

    private String name;

    public void updateBoard(MentoringRecruitSaveDto dto){
        this.title = dto.getTitle();
        this.role = dto.getRole();
        this.field = dto.getField();
        this.region = dto.getRegion();
        this.r_period = dto.getR_period();
        this.m_period = dto.getM_period();
        this.way = dto.getWay();
        this.keyword = dto.getKeyword();
        this.info = dto.getInfo();
    }
}
