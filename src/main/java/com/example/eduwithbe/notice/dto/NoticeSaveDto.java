package com.example.eduwithbe.notice.dto;

import com.example.eduwithbe.mentoring.domain.MentoringApplyEntity;
import com.example.eduwithbe.notice.domain.NoticeEntity;
import com.example.eduwithbe.user.domain.UserEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeSaveDto {

    private String title;
    private Date date;
    private String field;
    private UserEntity user;

    public NoticeEntity toEntity(){
        return NoticeEntity.builder()
                .title(title)
                .date(date)
                .field(field)
                .user(user)
                .build();
    }

//    public NoticeSaveDto(NoticeEntity noticeEntity) {
//        this.title = noticeEntity.getTitle();
//        this.date = noticeEntity.getDate();
//        this.field = noticeEntity.getField();
//        this.user = noticeEntity.getUser();
//    }
}
