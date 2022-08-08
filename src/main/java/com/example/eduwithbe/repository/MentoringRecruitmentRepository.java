package com.example.eduwithbe.repository;

import com.example.eduwithbe.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.domain.UserEntity;
import com.example.eduwithbe.dto.MentoringRecruitSaveDto;
import com.example.eduwithbe.dto.MentoringRecruitSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentoringRecruitmentRepository extends JpaRepository<MentoringRecruitmentEntity, Long> {
    List<MentoringRecruitmentEntity> findByTitleContaining(String keyword);

    @Query("select p from MentoringRecruitmentEntity p where p.field In(:field) and p.region In(:region) and p.m_period In(:m_period) and p.way In(:way)")
    List<MentoringRecruitmentEntity> findByFilter(@Param("field") List<String> field, @Param("region") List<String> region, @Param("m_period") List<Integer> m_period, @Param("way") List<String> way);

    @Query("select m from MentoringRecruitmentEntity m where m.email = :email and m.role = 'O'")
    List<MentoringRecruitmentEntity> findByMentoringMentor(@Param("email") String email);

    @Query("select m from MentoringRecruitmentEntity m where m.email = :email and m.role = 'E'")
    List<MentoringRecruitmentEntity> findByMentoringMentee(@Param("email") String email);
}


