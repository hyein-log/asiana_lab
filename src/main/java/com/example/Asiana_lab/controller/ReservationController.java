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
import java.util.Map;


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
            if (!reservationService.getIsAvailable(flight_no,seat_no)){//예약이 불가능 하다면
                seatAvail[seat_no-1] = true;
                System.out.println("예약 불가능한 좌석:" + seat_no);
            }
        }

        model.addAttribute("flight",reservationService.getFlight(flight_no));
        model.addAttribute("seatAvail",seatAvail);
        return "/reservation/reservationDetail";
    }

    @RequestMapping("/reservationCommit")
    public String reservationCommit(@RequestParam(required = false, defaultValue = "") int flight_no,
                                    @RequestParam(required = false, defaultValue = "") int user_no,
                                    @RequestParam(required = false, defaultValue = "") int seat_no, Model model) throws Exception {

        String userid = userService.findIdByNo(user_no);

        // 예약 정보 DB insert
        reservationService.reserve(user_no,seat_no);
        // 좌석정보 DB update
        reservationService.updateSeat(flight_no,seat_no);
        // 예약 번호 불러오기
        int reservatio_no = reservationService.getReservationInfo(user_no,seat_no);
        //reservationCommit에 data 넘기기
        model.addAttribute("flight",reservationService.getFlight(flight_no));
        model.addAttribute("user_id",userid);
        model.addAttribute("seat_no",seat_no);
        model.addAttribute("reservation_no",reservatio_no);

        return "/reservation/reservationCommit";
    }
}
