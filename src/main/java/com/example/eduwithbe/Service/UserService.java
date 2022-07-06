package com.example.eduwithbe.Service;

import com.example.eduwithbe.dto.UserLoginDTO;
import com.example.eduwithbe.dto.UserSaveDTO;

import java.util.Map;

public interface UserService {

    Map<String, Object> save(UserSaveDTO userSaveDTO);

    boolean login(UserLoginDTO userLoginDTO);

}
