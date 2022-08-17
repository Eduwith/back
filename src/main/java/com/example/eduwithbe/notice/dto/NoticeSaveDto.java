package com.example.eduwithbe.notice.dto;

import com.example.eduwithbe.notice.domain.NoticeEntity;
import com.example.eduwithbe.user.domain.UserEntity;
import lombok.*;

import java.sql.Date;

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
    private String read;

    public NoticeEntity toEntity() {
        return NoticeEntity.builder()
                .title(title)
                .date(date)
                .field(field)
                .check_read(read)
                .user(user)
                .build();
    }
}