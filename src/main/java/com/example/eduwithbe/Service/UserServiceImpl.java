package com.example.eduwithbe.Service;

import com.example.eduwithbe.Service.UserService;
import com.example.eduwithbe.domain.UserEntity;
import com.example.eduwithbe.dto.UserLoginDTO;
import com.example.eduwithbe.dto.UserSaveDTO;
import com.example.eduwithbe.repository.UserRepository;
import com.example.eduwithbe.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository mr;

    @Override
    public Map<String, Object> save(UserSaveDTO userSaveDTO) {
        Map<String, Object> response = new HashMap<>();

        UserEntity userEntity = UserEntity.saveUser(userSaveDTO);

        if (userEntity.getEmail() != null & userEntity.getPwd() != null
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
        Optional<UserEntity> userEntity = mr.findByEmail(userLoginDTO.getEmail());
        if (userEntity !=null) {
            return userEntity.get().getName().equals(userLoginDTO.getPwd());
        } else {
            return false;
        }
    }

    public UserEntity getUserFromAuth(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByUserName(authentication.getName());
//        User user = userRepository.findByEmail(authentication.getName());
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return user;

    }
}