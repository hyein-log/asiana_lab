package com.example.Asiana_lab.controller;

import com.example.Asiana_lab.model.dto.Seat;
import com.example.Asiana_lab.model.service.ReservationService;
import com.example.Asiana_lab.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

@Controller
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String reservationMain(Model model){
        model.addAttribute("flights",reservationService.getFlightList());
        return "/reservation/main";
    }
    @RequestMapping("/read_flight/{flight_no}")
    public String openReservationDetail(@PathVariable("flight_no") int flight_no,
                                        @RequestParam HashMap query, Model model){

        boolean[] seatAvail = new boolean[10];

        for (Object key : query.keySet()){
            int index = Integer.parseInt(key.toString().replace("ans",""));
            seatAvail[index - 1] = true;
        }
        model.addAttribute("flight",reservationService.getFlight(flight_no));
        model.addAttribute("seats",reservationService.getSeat(flight_no));

        return "/reservation/reservationDetail";
    }

    @RequestMapping("/reservationCommit/{flight_no}")
    public String reservationCommit(@PathVariable("flight_no") int flight_no,
                                    @RequestParam int seat_no, Model model){
        // 예약 정보 DB insert
        //reservationService.reserve(user_no,seat_no);
        //reservationCommit에 data 넘기기
        model.addAttribute("flight",reservationService.getFlight(flight_no));
        model.addAttribute("seat_no",seat_no);


        return "reservationCommit";
    }
}
