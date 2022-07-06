package com.example.eduwithbe.repository;

import com.example.eduwithbe.Service.MemberService;
import com.example.eduwithbe.domain.UserEntity;
import com.example.eduwithbe.dto.MemberLoginDTO;
import com.example.eduwithbe.dto.MemberSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    // Repository 생성자 주입.
    private final MemberRepository mr;

    // 회원가입 정보 저장
    @Override
    public Long save(MemberSaveDTO memberSaveDTO) {
        // JPARepository는 무조건 Entity 타입만 받기 때문에 Entity 타입으로 바꿔줘야함.
        UserEntity userEntity = UserEntity.saveMember(memberSaveDTO);
//        Long memberId = mr.save(memberEntity).getId();
//        return memberId;
        // 위 두줄과 아래 한줄은 같은 코드
        // 앞으로 이런식으로 줄여서 사용할 예정임.
        return mr.save(userEntity).getEmail();
    }

    @Override
    public boolean login(MemberLoginDTO memberLoginDTO) {
        // MemberEntity타입의 객체 생성 후 jpa의 findBy 메서드 호출 및 정보 저장
        // MemberLoginDTO의 memberEmail을 보내 값을 memberEntity에 담는것임.
        UserEntity userEntity = mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
        // 정보가 비어있으면 로그인시도를 한 email을 가진 데이터 자체가 없는 정보라는 뜻임.
        if (userEntity !=null) {
            // 로그인을 시도한 데이터의 비밀번호와 jpa에서 받아온 데이터의 비밀번호를 비교
            if(userEntity.getPwd().equals(memberLoginDTO.getMemberPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}