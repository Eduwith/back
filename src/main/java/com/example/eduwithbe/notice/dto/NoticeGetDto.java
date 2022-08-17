package com.example.eduwithbe.notice.dto;

import com.example.eduwithbe.notice.domain.NoticeEntity;
import lombok.*;

import java.sql.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeGetDto {
    private Long notice_no;
    private String title;
    private Date date;
    private String field;
    private String read;

    @Builder
    public NoticeGetDto(NoticeEntity noticeEntity) {
        this.notice_no = noticeEntity.getNotice_no();
        this.title = noticeEntity.getTitle();
        this.date = noticeEntity.getDate();
        this.field = noticeEntity.getField();
        this.read = noticeEntity.getCheck_read();
    }
}
