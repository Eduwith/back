package com.example.eduwithbe.user.service;

import com.example.eduwithbe.mentoring.domain.MentoringApplyEntity;
import com.example.eduwithbe.mentoring.domain.MentoringRecruitmentEntity;
import com.example.eduwithbe.mentoring.dto.MentoringApplySaveDto;
import com.example.eduwithbe.mentoring.repository.MentoringRecruitScrapRepositoty;
import com.example.eduwithbe.mentoring.repository.MentoringRecruitmentRepository;
import com.example.eduwithbe.user.domain.UserEntity;
import com.example.eduwithbe.user.dto.UserSaveDTO;
import com.example.eduwithbe.user.dto.UserUpdateDto;
import com.example.eduwithbe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository ur;

    @Autowired
    private final MentoringRecruitmentRepository mrr;

    @Autowired
    private final MentoringRecruitScrapRepositoty msr;

    SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:sss");
    Date time = new Date();
    String localTime = format.format(time);

    //유저 생성
    public Map<String, Object> save(UserSaveDTO userSaveDTO) {
        Map<String, Object> response = new HashMap<>();

        UserEntity userEntity = UserEntity.saveUser(userSaveDTO);

        if (userEntity.getEmail() != null & userEntity.getPwd() != null
                & userEntity.getAge() != 0 & userEntity.getName() != null
                & (userEntity.getGender() == 'M' || userEntity.getGender() == 'F')) {
            ur.save(userEntity);
            response.put("result", "SUCCESS");
            response.put("user", userEntity);
        } else {
            response.put("result", "FAIL");
            response.put("reason", "회원 가입 실패");
        }
        return response;
    }

    //유저 중복 이메일 확인
    public boolean existsByEmail(String email){
        return ur.existsByEmail(email);
    }

    //유저 정보 수정
    public void updateUser(String email, UserUpdateDto userUpdateDto) {
        ur.updateByUser(email, userUpdateDto.getName(), userUpdateDto.getPwd(), userUpdateDto.getAddress());
    }

    //유저 출석체크
    public void updateUserPoint(String email, int stamp, int point) {
        ur.updateByUserPoint(email, stamp, point);
    }


    //유저 멘토링 스크랩
    public String saveMentoringRecruitScrap(String email, Long m_no) {
        UserEntity userEntity = ur.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다." + email));
        MentoringRecruitmentEntity mentoringRecruitment = mrr.findById(m_no).orElseThrow(() -> new IllegalArgumentException("해당 멘토링이 존재하지 않습니다." + m_no));

        MentoringApplySaveDto mentoringApplySaveDto = new MentoringApplySaveDto();

        mentoringApplySaveDto.setEmail(userEntity.getEmail());
        mentoringApplySaveDto.setName(userEntity.getName());
        mentoringApplySaveDto.setM_no(mentoringRecruitment);

        MentoringApplyEntity apply = mentoringApplySaveDto.toEntity();
        //msr.save(apply);

        //MentoringApplyEntity mentoringApply = mr.save(dto.toEntity());
        return "OK";
    }

    public UserEntity getUserFromAuth(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByUserName(authentication.getName());
//        User user = userRepository.findByEmail(authentication.getName());
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return user;

    }

}
