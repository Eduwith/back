package com.example.eduwithbe.Study.Repository;

import com.example.eduwithbe.Study.Domain.StudyApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudyApplyRepository extends JpaRepository<StudyApplyEntity, Long> {
    // 특정 모집글에 대한 신청 현황 조회
    @Query(value = "SELECT sa FROM StudyApplyEntity sa WHERE sa.studyRecruitment.s_no=:s_no")
    List<StudyApplyEntity> findAppliesByStdNo(Long s_no);

    // 스터디 수락&거절 수정
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE StudyApplyEntity sa SET sa.result=:result WHERE sa.apply_no=:apply_no")
    int updateApplyResult(@Param("apply_no") Long apply_no, @Param("result") char result);
}
