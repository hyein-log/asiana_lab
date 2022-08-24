package com.example.Asiana_lab.controller;

import com.example.Asiana_lab.model.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @RequestMapping("/")
    public String reservationMain(Model model){
        model.addAttribute("flights",reservationService.getFlightList());
        return "/reservation/main";
    }
    @RequestMapping("/reservation_read/{flight_no}")
    public String openReservationDetail(@PathVariable("flight_no") int flight_no,Model model){
        return "reservationDetail";
    }
}
