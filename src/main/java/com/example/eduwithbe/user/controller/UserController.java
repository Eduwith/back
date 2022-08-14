package com.example.eduwithbe.user.controller;

import com.example.eduwithbe.mentoring.dto.MentoringApplyEmailDto;
import com.example.eduwithbe.mentoring.dto.MentoringRecruitUpdateDto;
import com.example.eduwithbe.mentoring.dto.ResultResponse;
import com.example.eduwithbe.user.dto.UserUpdateDto;
import com.example.eduwithbe.user.service.UserService;
import com.example.eduwithbe.user.domain.UserEntity;
import com.example.eduwithbe.user.dto.UserLoginDTO;
import com.example.eduwithbe.user.dto.UserSaveDTO;
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

    // 로그인
    @PostMapping("/login")
    public Token login(@RequestBody @Validated UserLoginDTO user) {
//        log.info("user email = {}", user.get("userEmail"));
        //UserEntity member = userRepository.findByEmail(user.getEmail())
        UserEntity member = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.getPwd(), member.getPwd())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        Token tokenDto = jwtTokenProvider.createAccessToken(member.getUsername(), member.getName(), member.getRoles());

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
    @GetMapping("/cattendance")
    public ResultResponse updateUserPoint(HttpServletRequest request){
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));
        UserEntity userEntity = userRepository.findByEmail(user).orElseThrow(() -> new IllegalArgumentException("신청 실패: 해당 유저가 존재하지 않습니다." + user));

        int point = 0;
        if(userEntity.getStamp()+1 == 7) point = userEntity.getPoint()+1000;
        us.updateUserPoint(user, userEntity.getStamp()+1, point);

        return new ResultResponse();
    }

    //회원수정
    @PatchMapping(value = "/edit")
    public ResultResponse updateMentoringRecruit(HttpServletRequest request, @RequestBody UserUpdateDto updateDto) {
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));
        updateDto.setPwd(passwordEncoder.encode(updateDto.getPwd()));
        us.updateUser(user, updateDto);
        //MentoringRecruitmentEntity updatedBoard = mentoringService.updateBoard(mentoringRecruitment, saveBoardDto);

        return new ResultResponse();
    }

    //회원탈퇴
    @DeleteMapping(value = "/Withdrawal")
    public ResultResponse saveMentoringApplyRefuse(HttpServletRequest request) {
        String user = jwtTokenProvider.getUserPk(request.getHeader("Authorization"));
        UserEntity userEntity = userRepository.findByEmail(user).orElseThrow(() -> new IllegalArgumentException("신청 실패: 해당 유저가 존재하지 않습니다." + user));

        userRepository.delete(userEntity);
        return new ResultResponse();
    }

}
