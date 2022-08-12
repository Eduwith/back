package com.example.eduwithbe.controller;

import com.example.eduwithbe.Service.UserService;
import com.example.eduwithbe.domain.UserEntity;
import com.example.eduwithbe.dto.UserLoginDTO;
import com.example.eduwithbe.dto.UserSaveDTO;
import com.example.eduwithbe.repository.UserRepository;
import com.example.eduwithbe.security.JwtService;
import com.example.eduwithbe.security.JwtTokenProvider;
import com.example.eduwithbe.security.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpHeaders;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    private UserService ms;

    @Autowired
    private JwtService jwtService;


    // 회원가입
    @PostMapping("/join")
    public Map<String, Object> join(@RequestBody UserSaveDTO user) {
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
        response = ms.save(user);
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
        Token tokenDto = jwtTokenProvider.createAccessToken(member.getUsername(), member.getRoles());

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
}
