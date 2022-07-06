package com.example.eduwithbe.Service;

import com.example.eduwithbe.dto.MemberLoginDTO;
import com.example.eduwithbe.dto.MemberSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface MemberService {

    Long save(MemberSaveDTO memberSaveDTO);

    // 내용 추가
    boolean login(MemberLoginDTO memberLoginDTO);

}
