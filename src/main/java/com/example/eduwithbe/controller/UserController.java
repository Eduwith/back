package com.example.eduwithbe.controller;

import com.example.eduwithbe.Service.UserService;
import com.example.eduwithbe.dto.UserLoginDTO;
import com.example.eduwithbe.dto.UserSaveDTO;
import lombok.RequiredArgsConstructor;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService ms;

    @Autowired
    public UserController(UserService ms) {
        this.ms = ms;
    }

//    @GetMapping("join") //회원가입 페이지 요청 //todo 링크 바꿈
//    public String saveForm() {
//        return "save";
//    }

    @PostMapping("/join") //회원가입 정보 저장
    public Map<String, Object> save(@RequestBody @Validated UserSaveDTO userSaveDTO) {

        Map<String, Object> response = new HashMap<>();
        response = ms.save(userSaveDTO);
        System.out.println(userSaveDTO.getEmail());
        System.out.println(userSaveDTO.getAge());
        System.out.println(response);
        return response;
    }

//    // 로그인 페이지 요청
//    @GetMapping("login")
//    public String loginForm() {
//        return "login";
//    }

    // 로그인 성공여부 판별
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody @Validated UserLoginDTO userLoginDTO, HttpSession session) {
        Map<String, Object> response = new HashMap<>();

        System.out.println(userLoginDTO.getEmail());
        System.out.println(userLoginDTO.getPwd());
        if (ms.login(userLoginDTO)) {
            session.setAttribute("email", userLoginDTO.getEmail());
            response.put("result", "SUCCESS");
        } else {
            response.put("result", "FAIL");
        }
        System.out.println(response);
        return response;
    }
}
