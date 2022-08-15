package com.example.eduwithbe.user.controller;

import com.example.eduwithbe.mentoring.dto.ResultResponse;
import com.example.eduwithbe.user.dto.*;
import com.example.eduwithbe.user.service.UserService;
import com.example.eduwithbe.user.domain.UserEntity;
import com.example.eduwithbe.user.repository.UserRepository;
import com.example.eduwithbe.security.JwtService;
import com.example.eduwithbe.security.JwtTokenProvider;
import com.example.eduwithbe.security.Token;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Api(tags = {"UserController"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    private UserService us;

    @Autowired
    private JwtService jwtService;


    // 회원가입
    @ApiOperation(value = "회원가입")
    @PostMapping("/join")
    public @ResponseBody Map<String, Object> join(@RequestBody UserSaveDTO user) {
        //user.setRoles(Collections.singletonList("ROLE_USER"));
        user.setPwd(passwordEncoder.encode(user.getPwd()));
        userRepository.save(UserEntity.builder()
                .email(user.getEmail())
                .name(user.getName())
                .gender(user.getGender())
                .age(user.getAge())
                .address(user.getAddress())
                .pwd(passwordEncoder.encode(user.getPwd()))
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build());

        Map<String, Object> response;
        response = us.save(user);
        return response;
    }

    //회원가입 체크
    @PostMapping("/join/check")
    public ResultResponse joinCheck(@RequestBody UserJoinCheckDto userJoinCheckDto) {
        ResultResponse resultResponse = new ResultResponse();

        if(us.existsByEmail(userJoinCheckDto.getEmail())) resultResponse.setResult("FAILURE");
        else resultResponse.setResult("SUCCESS");

        return resultResponse;
    }

    // 로그인
    @PostMapping("/login")
    public Token login(@RequestBody @Validated UserLoginDTO user) {
//        log.info("user email = {}", user.get("userEmail"));
        //UserEntity member = userRepository.findByEmail(user.getEmail())
        UserEntity userEntity = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.getPwd(), userEntity.getPwd())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        Token tokenDto = jwtTokenProvider.createAccessToken(userEntity.getUsername(), userEntity.getName(), userEntity.getRoles());

        return tokenDto;
    }

    //로그인 체크
    @PostMapping("/loginCheck")
    public Map<String, String> check(HttpServletRequest request) {
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));

        Optional<UserEntity> userEntity = userRepository.findByEmail(user);

        Map<String, String> map = new ManagedMap<>();
        map.put("email", user);
        map.put("name" , userEntity.get().getName());
        return map;
    }

    //출석체크
    @PatchMapping("/cattendance")
    public UserCattendanceDto updateUserPoint(HttpServletRequest request){
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));
        UserEntity userEntity = userRepository.findByEmail(user).orElseThrow(() -> new IllegalArgumentException("신청 실패: 해당 유저가 존재하지 않습니다." + user));

        int stamp = userEntity.getStamp()+1;
        int point = 100*(stamp/7);
        System.out.println(stamp/7);

        us.updateUserPoint(user, stamp, point);

        return new UserCattendanceDto(stamp, point);
    }

    //회원수정
    @PatchMapping(value = "/edit")
    public ResultResponse updateMentoringRecruit(HttpServletRequest request, @RequestBody UserUpdateDto updateDto) {
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));
        UserEntity userEntity = userRepository.findByEmail(user)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        ResultResponse resultResponse = new ResultResponse();
        if(!passwordEncoder.matches(updateDto.getPwd(), userEntity.getPassword())) {
            resultResponse.setResult("잘못된 비밀번호입니다.");
        }
        else {
            resultResponse.setResult("SUCCESS");
            updateDto.setPwd(passwordEncoder.encode(updateDto.getChangePwd()));
            us.updateUser(user, updateDto);
        }

        return new ResultResponse();
    }

    //멘토링 모집글 스크랩
    @PostMapping(value = "/scrap/mentoring")
    public ResultResponse saveMentoringRecruit(HttpServletRequest request, @RequestBody Long m_no) {
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));
        us.saveMentoringRecruitScrap(user, m_no);

        return new ResultResponse();
    }

    //회원탈퇴
    @DeleteMapping(value = "/withdrawal")
    public ResultResponse saveMentoringApplyRefuse(HttpServletRequest request) {
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));
        UserEntity userEntity = userRepository.findByEmail(user).orElseThrow(() -> new IllegalArgumentException("신청 실패: 해당 유저가 존재하지 않습니다." + user));

        userRepository.delete(userEntity);
        return new ResultResponse();
    }

    //유저-마이페이지-프로필수정
    @GetMapping(value = "/mypage")
    public UserInfoDto getMyPage(HttpServletRequest request) {
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));
        UserEntity userEntity = userRepository.findByEmail(user).orElseThrow(() -> new IllegalArgumentException("신청 실패: 해당 유저가 존재하지 않습니다." + user));

        return new UserInfoDto(userEntity);
    }

}
