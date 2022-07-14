package com.example.eduwithbe.controller;

import com.example.eduwithbe.Service.UserService;
import com.example.eduwithbe.domain.UserEntity;
import com.example.eduwithbe.dto.UserLoginDTO;
import com.example.eduwithbe.dto.UserSaveDTO;
import com.example.eduwithbe.repository.UserRepository;
import com.example.eduwithbe.repository.UserServiceImpl;
import com.example.eduwithbe.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/user")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    private UserService ms;

    // 회원가입
    @PostMapping("/join")
    public Map<String, Object> join(@RequestBody UserSaveDTO user) {
        //user.setRoles(Collections.singletonList("ROLE_USER"));
        user.setPwd(passwordEncoder.encode(user.getPwd()));
//        userRepository.save(UserEntity.builder()
//                .email(user.getEmail())
//                .name(user.getName())
//                .gender(user.getGender())
//                .age(user.getAge())
//                .address(user.getAddress())
//                .pwd(passwordEncoder.encode(user.getPwd()))
//                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
//                .build());
        Map<String, Object> response;
        response = ms.save(user);
        return response;



    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody @Validated UserLoginDTO user) {
        UserEntity member = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.getPwd(), member.getPwd())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        String s = jwtTokenProvider.createToken(member.getUsername(), member.getAuthorities());
        System.out.println(s);
        return s;
    }

//    @GetMapping("join") //회원가입 페이지 요청 //todo 링크 바꿈
//    public String saveForm() {
//        return "save";
//    }

//    @PostMapping("/join") //회원가입 정보 저장
//    public Map<String, Object> save(@RequestBody @Validated UserSaveDTO userSaveDTO) {
//
//        Map<String, Object> response = new HashMap<>();
//        response = ms.save(userSaveDTO);
//        System.out.println(userSaveDTO.getEmail());
//        System.out.println(userSaveDTO.getAge());
//        System.out.println(response);
//        return response;
//    }
//
////    // 로그인 페이지 요청
////    @GetMapping("login")
////    public String loginForm() {
////        return "login";
////    }
//
//    // 로그인 성공여부 판별
//    @PostMapping("/login")
//    public Map<String, Object> login(@RequestBody @Validated UserLoginDTO userLoginDTO, HttpSession session) {
//        Map<String, Object> response = new HashMap<>();
//
//        System.out.println(userLoginDTO.getEmail());
//        System.out.println(userLoginDTO.getPwd());
//        if (ms.login(userLoginDTO)) {
//            session.setAttribute("email", userLoginDTO.getEmail());
//            response.put("result", "SUCCESS");
//        } else {
//            response.put("result", "FAIL");
//        }
//        System.out.println(response);
//        return response;
//    }
}
