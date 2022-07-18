package com.example.eduwithbe.Service;

import com.example.eduwithbe.domain.Volunteer;
import com.example.eduwithbe.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    // 모집 글 전체 조회
    public List<Volunteer> volunteerList() {
        return volunteerRepository.findAll();
    }

//    // 특정 모집글 상세 보기
//    public Volunteer volunteerDetailView(Long no) {
//        Optional<Volunteer> vol = volunteerRepository.findById(no);
//        if(vol.isPresent()) {
//            return vol.get();
//        }
//
//        throw new EntityNotFoundException(
//                "Cant find any volunteer under given No."
//        );
//    }


}
