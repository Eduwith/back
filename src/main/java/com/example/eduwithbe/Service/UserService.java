package com.example.eduwithbe.Service;

import com.example.eduwithbe.domain.UserEntity;
import com.example.eduwithbe.dto.UserLoginDTO;
import com.example.eduwithbe.dto.UserSaveDTO;
import com.example.eduwithbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository mr;

    SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:sss");
    Date time = new Date();
    String localTime = format.format(time);


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


    public UserEntity getUserFromAuth(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByUserName(authentication.getName());
//        User user = userRepository.findByEmail(authentication.getName());
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return user;

    }

}
