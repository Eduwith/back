package com.example.eduwithbe.notice.domain;

import com.example.eduwithbe.user.domain.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="notice")
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_no")
    private Long notice_no;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String field;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private UserEntity user;

}
