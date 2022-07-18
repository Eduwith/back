package com.example.eduwithbe.controller;

import com.example.eduwithbe.Service.VolunteerService;
import com.example.eduwithbe.domain.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/volunteers")
public class VolunteerController {
    @Autowired
    public VolunteerService volunteerService;

    // 전체 목록 조회
    @GetMapping("")
    public List<Volunteer> volunteerList() {
        List<Volunteer> vols = volunteerService.volunteerList();
        for (Volunteer v:vols) {
            System.out.println(v.toString());
        }
        return vols;
    }

//    // 특정 글 조회
//    @GetMapping("/view") // localhost:8080/api/volunteers/view?no=1
//    public String volunteerDetailView(Model model, Long no) {
//        model.addAttribute("no_volunteer", volunteerService.volunteerDetailView(no));
//        return "volunteerView";
//    }

    /*
    @GetMapping("/volunteer/write") // localhost:8080/volunteer/write
    public String volunteerWriteForm() {
        return "volunteerWrite";
    }

    @PostMapping("/volunteer/writePro")
    public String volunteerWritePro(Volunteer volunteer) {
        System.out.println(volunteer);
        System.out.println("제목 : " + volunteer.getTitle());

        System.out.println("내용 : " + volunteer.getContents());
        System.out.println("주소 : " + volunteer.getAddress());
        System.out.println("모집 시작 날짜 : " + volunteer.getR_start_date());
        System.out.println("모집 종료 날짜 : " + volunteer.getR_end_date());
        System.out.println("봉사 시작 날짜 : " + volunteer.getV_start_date());
        System.out.println("봉사 종료 날짜 : " + volunteer.getV_end_date());
        System.out.println("모집 전체 인원 : " + volunteer.getTotal_people());
        System.out.println("현재 인원 : " + volunteer.getCurrent_people());


        volunteerService.write(volunteer);

        return "";
    }

    @GetMapping("/volunteer/delete")
    public String volunteerDelete(Long id) {
        volunteerService.volunteerDelete(id);

        return "redirect:/volunteer/list";
    }
    */
}