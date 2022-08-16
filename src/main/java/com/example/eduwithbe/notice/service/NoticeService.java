package com.example.eduwithbe.notice.service;

import com.example.eduwithbe.notice.domain.NoticeEntity;
import com.example.eduwithbe.notice.dto.NoticeSaveDto;
import org.springframework.stereotype.Service;

@Service
public interface NoticeService {

    NoticeEntity saveNotice(NoticeSaveDto dto);
}
