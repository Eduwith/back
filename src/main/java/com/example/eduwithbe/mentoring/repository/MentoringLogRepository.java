package com.example.eduwithbe.mentoring.repository;

import com.example.eduwithbe.mentoring.domain.MentoringLogEntity;
import com.example.eduwithbe.mentoring.domain.MentoringRecruitmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MentoringLogRepository extends JpaRepository<MentoringLogEntity, Long> {
    @Query("select m from MentoringLogEntity m where m.mentoring_no.mentoring_no = :mentoring_no")
    List<MentoringLogEntity> findByMentoringIdLog(Long mentoring_no);

    @Transactional
    @Modifying
    @Query("update MentoringLogEntity m set m.title = :title, m.content = :content  where m.log_no = :log_no")
    void updateByMentoringLog(Long log_no, String title, String content);
}
