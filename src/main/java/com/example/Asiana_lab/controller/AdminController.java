package com.example.Asiana_lab.controller;

import com.example.Asiana_lab.model.dto.Flight;
import com.example.Asiana_lab.model.service.AdminService;
import com.example.Asiana_lab.model.service.AirportService;
import com.example.Asiana_lab.model.service.ReservationService;
import com.example.Asiana_lab.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private AirportService airportService;

    //여정 추가
    @GetMapping("/insert")
    public String add(@RequestParam String date, @RequestParam String hour, @RequestParam String minute, @RequestParam int departure, @RequestParam int destination) {

        System.out.println(date + hour + minute);

        String dep_time = date.substring(6, 10) + "-" + date.substring(0, 2) + "-" + date.substring(3, 5);
        System.out.println(dep_time + "@@@@@@@@@@@@@@@");

        adminService.addFlight(new Flight(dep_time+"+"+hour+":"+minute, departure, destination));


        return "redirect:/admin/main";
    }

    //여정 수정
    @PutMapping("/update")
    public ResponseEntity<String> update(Flight flight) {
        if(adminService.modifyFlight(flight)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
        }
    }

    //여정 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int flight_id){
        if(adminService.deleteFlight(flight_id)){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    //여정 조회 (어드민 메인 페이지)
    @GetMapping("/main")
    public String list(Model model) {

        model.addAttribute("flights", reservationService.getFlightList());
        model.addAttribute("airports",airportService.getAirportList());
        return "/admin/adminMain";
    }

    //여정 상세 및 등록 폼 호출
    @GetMapping("/form")
    public String form(Model model) {

        model.addAttribute("airports", airportService.getAirportList());

        return "/admin/form";
    }

    //여정 선택
//    @GetMapping("/{id}")
//    public ResponseEntity<Flight> selectFlight(@PathVariable("id") int flight_no) {
//        Flight flight = adminService.getFlightById(flight_no);
//
//        return new ResponseEntity<Flight>(flight, HttpStatus.OK);
//    }


}
