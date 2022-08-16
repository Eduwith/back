package com.example.eduwithbe.notice.repository;

import com.example.eduwithbe.notice.domain.NoticeEntity;
import com.example.eduwithbe.user.domain.UserEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

}
