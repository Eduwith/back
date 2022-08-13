package com.example.eduwithbe.user.service;

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

    SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:sss");
    Date time = new Date();
    String localTime = format.format(time);


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

    public void updateUser(String email, UserUpdateDto userUpdateDto) {
        ur.updateByUser(email, userUpdateDto.getName(), userUpdateDto.getPwd(), userUpdateDto.getAddress());
    }

    public void updateUserPoint(String email, int stamp, int point) {
        ur.updateByUserPoint(email, stamp, point);
    }

    public UserEntity getUserFromAuth(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByUserName(authentication.getName());
//        User user = userRepository.findByEmail(authentication.getName());
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return user;

    }

}
