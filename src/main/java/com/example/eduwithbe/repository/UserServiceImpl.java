package com.example.eduwithbe.repository;

import com.example.eduwithbe.Service.UserService;
import com.example.eduwithbe.domain.UserEntity;
import com.example.eduwithbe.dto.UserLoginDTO;
import com.example.eduwithbe.dto.UserSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository mr;

    @Override
    public Map<String, Object> save(UserSaveDTO userSaveDTO) {
        Map<String, Object> response = new HashMap<>();

        UserEntity userEntity = UserEntity.saveUser(userSaveDTO);

        if(userEntity.getEmail() != null & userEntity.getPwd() != null
                & userEntity.getAge() != 0 & userEntity.getName() != null
                & (userEntity.getGender() == 'M' || userEntity.getGender() == 'F')) {
            mr.save(userEntity);
            response.put("result", "SUCCESS");
            response.put("user", userEntity);
        } else {
            response.put("result", "FAIL");
            response.put("reason", "회원 가입 실패");
        }
        return response;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        UserEntity userEntity = mr.findByEmail(userLoginDTO.getEmail());
        if (userEntity !=null) {
            return userEntity.getPwd().equals(userLoginDTO.getPwd());
        } else {
            return false;
        }
    }
}