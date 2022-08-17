package com.example.eduwithbe.Study.Service;

import com.example.eduwithbe.Study.Domain.StudyApplyEntity;
import com.example.eduwithbe.Study.Domain.StudyRecruitmentEntity;
import com.example.eduwithbe.Study.Domain.StudyingEntity;
import com.example.eduwithbe.Study.Dto.MyStudyDto;
import com.example.eduwithbe.Study.Dto.StudyApplyInfoDto;
import com.example.eduwithbe.Study.Dto.UserDetailDto;
import com.example.eduwithbe.Study.Repository.StudyApplyRepository;
import com.example.eduwithbe.Study.Repository.StudyRepository;
import com.example.eduwithbe.Study.Repository.StudyingRepository;
import com.example.eduwithbe.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StudyAdminServiceImpl implements StudyAdminService{
    private final StudyRepository studyRepository;
    private final StudyApplyRepository studyApplyRepository;
    private final StudyingRepository studyingRepository;


    // 내가 등록한 스터디 모집글 확인
    @Override
    public List<StudyRecruitmentEntity> findMyStudyRecruits(String myEmail) {
        return studyRepository.findByUserEmail(myEmail);
    }

    // 스터디 모집글 신청 현황
    @Override
    public List<StudyApplyEntity> findStudyApplyOfMyStudy(Long s_no) {
        return studyApplyRepository.findAppliesByStdNo(s_no);
    }

    @Override
    @Transactional
    // 내가 등록한 스터디 모집글 & 신청 현황 조회
    public List<MyStudyDto> findMyStudies(String myEmail) {
        List<MyStudyDto> result = new ArrayList<>();

        List<StudyRecruitmentEntity> myRecruits = studyRepository.findByUserEmail(myEmail); // 내가 등록한 스터디 모집글 리스트

        for (StudyRecruitmentEntity r :myRecruits) {
//            applyInfo.clear();
            List<StudyApplyInfoDto> applyInfo = new ArrayList<>();
            Long s_no = r.getS_no(); // 스터디 글번호 추출
            List<StudyApplyEntity> applies = studyApplyRepository.findAppliesByStdNo(s_no); // 신청 현황 가져오기
            System.out.println("======== s_no : " + s_no);
            System.out.println("******** applies.size() " + applies.size());

            if(applies.size() > 0) {
                System.out.println("applies.size() > 0 내부");
                for (StudyApplyEntity sa : applies) {
                    UserEntity applicant = sa.getUser();    // 신청자 정보 추출
                    UserDetailDto userDetail = UserDetailDto.builder()  // 프론트로 보낼 신청자의 정보 만들기
                            .profile_img("")
                            .name(applicant.getName())
                            .age(applicant.getAge())
                            .email(applicant.getEmail())
                            .build();

                    // apply info 저장
                    applyInfo.add(StudyApplyInfoDto.builder()
                            .s_no(s_no)
                            .apply(sa)
                            .userDetail(userDetail)
                            .build());
                }
            }
            result.add(MyStudyDto.builder()
                    .study(r)
                    .applies(applyInfo)
                    .build());
        }

        return result;
    }

    // 내 스터디 신청자 확인
    public UserDetailDto findUserDetail(Long apply_no) {
        // 신청자 추출
        UserEntity applicant = studyApplyRepository.findById(apply_no)
                .orElseThrow(() -> new IllegalArgumentException("findUserDetail : 해당 지원 정보가 없습니다."))
                .getUser();

        return UserDetailDto.builder()
                .profile_img("")
                .email(applicant.getEmail())
                .name(applicant.getName())
                .age(applicant.getAge())
                .build();

    }

    // 신청자 수락하기
    @Override
    public String saveStudying(Long stdNo, Long apply_no) {
        StudyApplyEntity studyApply = studyApplyRepository.findById(apply_no)
                .orElseThrow(() -> new IllegalArgumentException("saveStudying : 지원 정보가 없습니다."));
        StudyRecruitmentEntity studyRecruitment = studyRepository.findById(stdNo)
                .orElseThrow(() -> new IllegalArgumentException("saveStudying : 해당 스터디가 없습니다."));

        // StudyApply의 result를 'Y'로 수정
        studyApplyRepository.updateApplyResult(apply_no, 'Y');
        // 스터디 모집글&신청자 이메일 정보 저장
        studyingRepository.save(StudyingEntity.builder()
                .studyRecruitment(studyRecruitment)
                .email(studyApply.getUser().getEmail())
                .build());

        return "success";
    }

    // 신청자 거절
    @Override
    public String deleteStudyApply(Long apply_no) {
        StudyApplyEntity studyApply = studyApplyRepository.findById(apply_no)
                .orElseThrow(() -> new IllegalArgumentException("deleteStudyApply : 지원 정보가 없습니다."));

        studyApplyRepository.delete(studyApply);
        return "success";
    }

    // 스터디 마감하기
    @Override
    public String finishStudy(Long stdNo, char recruitYN) {
        studyRepository.updateRecruitYN(stdNo, recruitYN);
        return "success";
    }

    @Override
    // 나의 매칭된 스터디 내역
    public List<StudyingEntity> findMyStudying(String myEmail) {
        return studyingRepository.findByEmail(myEmail);
    }
}
