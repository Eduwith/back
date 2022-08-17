package com.example.eduwithbe.mentoring.controller;


import com.example.eduwithbe.mentoring.domain.MentoringApplyEntity;
import com.example.eduwithbe.mentoring.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.mentoring.dto.MentoringApplyEmailDto;
import com.example.eduwithbe.mentoring.dto.MentoringRecruitDto;
import com.example.eduwithbe.mentoring.dto.ResultResponse;
import com.example.eduwithbe.mentoring.repository.MentoringApplyRepository;
import com.example.eduwithbe.mentoring.repository.MentoringRecruitmentRepository;
import com.example.eduwithbe.mentoring.service.MentoringApplyService;
import com.example.eduwithbe.mentoring.service.MentoringService;
import com.example.eduwithbe.notice.dto.NoticeSaveDto;
import com.example.eduwithbe.notice.service.NoticeService;
import com.example.eduwithbe.security.JwtTokenProvider;
import com.example.eduwithbe.user.domain.UserEntity;
import com.example.eduwithbe.user.dto.UserMentoringApplyDetailDTO;
import com.example.eduwithbe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class MentoringApplyController {

    private final MentoringApplyService mentoringApplyService;
    private final MentoringService mentoringService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MentoringApplyRepository mentoringApplyRepository;
    private final MentoringRecruitmentRepository mentoringRecruitmentRepository;
    private final UserRepository userRepository;
    private final NoticeService noticeService;

    //마이페이지 멘토링 신청 리스트
    @GetMapping("/mypage/apply")
    public List<MentoringApplyEmailDto> findByApplyEmail(HttpServletRequest request){
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));
        return mentoringApplyService.findByEmail(user);
    }

    //멘토링 멘티/멘토 지원
    @PostMapping(value = "/mentoring/{m_no}/apply")
    public ResultResponse saveMentoringApply(HttpServletRequest request, @PathVariable Long m_no) {
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));
        String s = mentoringApplyService.saveMentoringApply(user, m_no);
        //UserEntity userEntity = userRepository.findByEmail(user).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다." + user));

        if(Objects.equals(s, "OK")){
            Optional<MentoringRecruitmentEntity> mentoringRecruitment = mentoringRecruitmentRepository.findById(m_no);

            String role;
            NoticeSaveDto noticeSaveDto = new NoticeSaveDto();
            if(Objects.equals(mentoringRecruitment.get().getRole(), "O")){
                role = "멘토";
            } else role = "멘티";
            noticeSaveDto.setTitle(mentoringRecruitment.get().getTitle() + " 멘토링 - 방금 새로운 " + role +"가 지원했어요.");
            noticeSaveDto.setUser(mentoringRecruitment.get().getUser());
            noticeSaveDto.setField("Mentoring");
            noticeSaveDto.setRead("N");
            noticeService.saveNotice(noticeSaveDto);
        }
        return new ResultResponse();
    }

    //멘토링 신청 수락
    @PostMapping(value = "/mypage/{m_no}/apply/{apply_no}")
    public ResultResponse saveMentoringApplyAccept(HttpServletRequest request, @PathVariable Long m_no, @PathVariable Long apply_no) {
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));
        String s = mentoringService.saveMentoring(user, m_no, apply_no);
        UserEntity userEntity = userRepository.findByEmail(user).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다." + user));

        if(Objects.equals(s, "OK")){
            Optional<MentoringRecruitmentEntity> mentoringRecruitment = mentoringRecruitmentRepository.findById(m_no);

            String role;
            NoticeSaveDto noticeSaveDto = new NoticeSaveDto();
            if(Objects.equals(mentoringRecruitment.get().getRole(), "O")){
                role = "멘토";
            } else role = "멘티";
            noticeSaveDto.setTitle(mentoringRecruitment.get().getTitle() + " 멘토링 - " + role + "신청이 수락되었어요.");
            noticeSaveDto.setUser(userEntity);
            noticeSaveDto.setField("Mentoring");
            noticeSaveDto.setRead("N");
            noticeService.saveNotice(noticeSaveDto);
        }
        return new ResultResponse();
    }

    //멘토링 신청 거절
    @DeleteMapping(value = "/mypage/{m_no}/apply/{apply_no}")
    public ResultResponse saveMentoringApplyRefuse(HttpServletRequest request, @PathVariable Long m_no, @PathVariable Long apply_no) {
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));
        String s = mentoringService.deleteMentoring(apply_no);
        UserEntity userEntity = userRepository.findByEmail(user).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다." + user));

        if(Objects.equals(s, "OK")){
            Optional<MentoringRecruitmentEntity> mentoringRecruitment = mentoringRecruitmentRepository.findById(m_no);

            String role;
            NoticeSaveDto noticeSaveDto = new NoticeSaveDto();
            if(Objects.equals(mentoringRecruitment.get().getRole(), "O")){
                role = "멘토";
            } else role = "멘티";
            noticeSaveDto.setTitle(mentoringRecruitment.get().getTitle() + " 멘토링 - " + role + "신청이 거절되었어요.");
            noticeSaveDto.setUser(userEntity);
            noticeSaveDto.setField("Mentoring");
            noticeSaveDto.setRead("N");
            noticeService.saveNotice(noticeSaveDto);
        }

        return new ResultResponse();
    }

    //멘토링 신청 취소
    @DeleteMapping(value = "/mypage/apoply/{apply_no}")
    public ResultResponse saveMentoringApplyRefuse(@PathVariable Long apply_no) {
        mentoringService.deleteMentoring(apply_no);
        return new ResultResponse();
    }

    //멘토링 신청 유저 프로필
    @GetMapping(value = "/mypage/{apply_no}/profile")
    public UserMentoringApplyDetailDTO findOneApplyUser(@PathVariable Long apply_no) {
        Optional<MentoringApplyEntity> mentoringApply = mentoringApplyRepository.findById(apply_no);
        Optional<UserEntity> user = userRepository.findByEmail(mentoringApply.get().getEmail());

        return UserMentoringApplyDetailDTO.builder()
                .email(user.get().getEmail())
                .name(user.get().getName())
                .age(user.get().getAge())
                .build();
    }

}
