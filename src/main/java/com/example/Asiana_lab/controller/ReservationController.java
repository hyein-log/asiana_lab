package com.example.Asiana_lab.controller;

import com.example.Asiana_lab.model.dto.Seat;
import com.example.Asiana_lab.model.dto.User;
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
import java.util.Map;
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
                                         Model model){
        // 해당 여정의 좌석의 예약 가능 여부를 받아서 seatAvail 리스트에 저장
        boolean[] seatAvail = new boolean[10];
        ArrayList<Seat> seats = reservationService.getSeat(flight_no);
        for (Seat seat : seats ){
            int seat_no = seat.getSeat_no();
            if (reservationService.getIsAvailable(flight_no,seat_no)){
                seatAvail[seat_no-1] = true;
            }
        }

        model.addAttribute("flight",reservationService.getFlight(flight_no));
        model.addAttribute("seats",reservationService.getSeat(flight_no));
        model.addAttribute("seat_availables",seatAvail);
        return "/reservation/reservationDetail";
    }

    @RequestMapping("/reservationCommit/{flight_no}")
    public String reservationCommit(@PathVariable("flight_no") int flight_no,
                                    @RequestParam(required = false, defaultValue = "") HashMap<String,String> query, Model model) throws Exception {
        String[] answers = new String[2];

        for(Map.Entry<String,String> entry : query.entrySet()){
            int index = Integer.parseInt(entry.getKey().replace("ans",""));
            answers[index-1] = entry.getValue();
        }
        int seat_no = Integer.parseInt(answers[0]);
        int user_no = Integer.parseInt(answers[1]);
        String userid = userService.findIdByNo(user_no);

        // 예약 정보 DB insert
        reservationService.reserve(user_no,seat_no);
        // 예약 번호 불러오기
        int reservatio_no = reservationService.getReservationInfo(user_no,seat_no);
        //reservationCommit에 data 넘기기
        model.addAttribute("flight",reservationService.getFlight(flight_no));
        model.addAttribute("user_id",userid);
        model.addAttribute("seat_no",seat_no);
        model.addAttribute("reservation_no",reservatio_no);

        return "reservationCommit";
    }
}
