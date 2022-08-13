package com.example.eduwithbe.UserTest.Service;

import com.example.eduwithbe.UserTest.DTO.UserTestSaveDto;
import com.example.eduwithbe.UserTest.Repository.UserTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTestService {
    private final UserTestRepository userTestRepository;

    // 테스트 결과 저장
    public String saveTestResult(String email, UserTestSaveDto utDto) {
        userTestRepository.save(utDto.toEntity(email));

        return "success";
    }

}
