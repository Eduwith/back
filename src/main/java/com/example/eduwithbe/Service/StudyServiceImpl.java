package com.example.eduwithbe.Service;

import com.example.eduwithbe.domain.StudyRecruitment;
import com.example.eduwithbe.dto.StudyRegisterRequestDto;
import com.example.eduwithbe.dto.StudyResponseDto;
import com.example.eduwithbe.mappers.StudyMapper;
import com.example.eduwithbe.paging.CommonParams;
import com.example.eduwithbe.paging.Pagination;
import com.example.eduwithbe.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService{

    private final StudyRepository studyRepository;
    private final StudyMapper studyMapper;


    @Override
    // 모든 스터디 모집글 조회 & 태그 검색 (With. Pagination)
    public Map<String, Object> getAllStudies(CommonParams params) {
        // 모집글 수 조회
        int count = studyMapper.count(params);

        // 등록된 모집글이 없는 경우, 로직 종료
        if(count < 1) {
            return Collections.emptyMap();
        }

        // 페이지네이션 정보 계산
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        // 모집글 리스트 조회
        List<StudyResponseDto> list = studyMapper.findAll(params);

        // 데이터 반환
        Map<String, Object> response = new HashMap<>();
        response.put("params", params);
        response.put("list", list);
        return response;
    }

//    @Override
//    public List<StudyResponseDto> getAllStudies() {
//        PageRequest pageRequest = PageRequest.of(0,6);
//        return studyRepository.findAll(pageRequest).stream().map(StudyResponseDto::new).collect(Collectors.toList());
//    }

    // 모집글 상세정보 조회
    @Override
    public StudyResponseDto getStudyByNo(final Long stdNo) {
        StudyRecruitment entity = studyRepository.findById(stdNo).orElseThrow(() -> new IllegalArgumentException("해당 글이 존재하지 않습니다.11"));
        return new StudyResponseDto(entity);
    }

    // 스터디 신청하기
//    @Override
//    @Transactional
//    public Long applyStudy(Long stdNo, String email) {
//        // 신청한 스터디 모집글 정보와 신청자(나) 정보 가져오기
//        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("applyStudy : 사용자를 찾을 수 없습니다."));
//        StudyRecruitment study = studyRepository.findById(stdNo).orElseThrow(() -> new IllegalArgumentException("applyStudy : 해당 글이 존재하지 않습니다."));
//
//        // 지원 정보 저장
//        StudyApply savedApply = studyApplyRepository.save(StudyApply.builder()
//                .result('P')
//                .user(user)
//                .studyRecruitment(study)
//                .build());
//
//        return savedApply.getApply_no();
//    }

    // 스터디 모집글 등록 페이지 이동
    @Override
    public void registerForm() {

    }

    // 스터디 모집글 등록
    @Override
    @Transactional
    public Long registerStudy(final StudyRegisterRequestDto studyReg) {
//        UserEntity user = userRepository.findByEmail(userEmail)
//                .orElseThrow(() -> new UsernameNotFoundException("StudyServiceImpl : 사용자를 찾을 수 없습니다."));
        StudyRecruitment savedStudy = studyRepository.save(studyReg.toEntity());

        return savedStudy.getS_no();
    }

    // 스터디 모집글 수정
    @Override
    @Transactional
    public Long updateStudy(final Long stdNo, final StudyRegisterRequestDto studyReq) {
        StudyRecruitment entity = studyRepository.findById(stdNo).orElseThrow(() -> new IllegalArgumentException("해당 글이 존재하지 않습니다.22"));
        entity.update(studyReq.getTitle(), studyReq.getContents(), studyReq.getTag(), studyReq.getTotal_people(),
                studyReq.getR_end_date(), studyReq.getS_period());
        return stdNo;
    }

    // 스터디 모집글 삭제
    @Override
    @Transactional
    public void deleteStudy(final Long stdNo) {
        studyRepository.deleteById(stdNo);
    }
}
