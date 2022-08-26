package com.example.Asiana_lab.controller;

import com.example.Asiana_lab.model.dto.Flight;
import com.example.Asiana_lab.model.dto.Seat;
import com.example.Asiana_lab.model.dto.Ticket;
import com.example.Asiana_lab.model.service.AirportService;
import com.example.Asiana_lab.model.service.ReservationService;
import com.example.Asiana_lab.model.service.TicketService;
import com.example.Asiana_lab.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@Controller
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @Autowired
    UserService userService;

    @Autowired
    AirportService airportService;

    @Autowired
    TicketService ticketService;

    @RequestMapping("/")
    public String reservationMain(Model model){
        model.addAttribute("flights",reservationService.getFlightList());
        model.addAttribute("airports",airportService.getAirportList());
        return "/reservation/main";
    }
    @RequestMapping("/read_flight/{flight_no}")
    public String openReservationDetail(HttpServletRequest request,
                                        @PathVariable("flight_no") int flight_no,
                                         Model model){
        // 로그인
        if(request.getSession().getAttribute("user_no") == null ){

            return "redirect:/login";
        }
        // 해당 여정의 좌석의 예약 가능 여부를 받아서 seatAvail 리스트에 저장
        boolean[] seatAvail = new boolean[10];
        ArrayList<Seat> seats = reservationService.getSeat(flight_no);
        for (Seat seat : seats ){

            System.out.println(seats.size());

            int seat_no = seat.getSeat_no();
            if (!reservationService.getIsAvailable(flight_no,seat_no)){//예약이 불가능 하다면
                seatAvail[seat_no-1] = true;
            }
        }
        // 로그인 한 회원이 해당 여정을 예약했다면 예약 못한다는 정보 전달.
        int user_no = (int) request.getSession().getAttribute("user_no");
        ArrayList<Ticket> tickets = ticketService.selectTicketInfo(user_no);
        for(Ticket ticket: tickets){
            if(ticket.getFlight_no() == flight_no){// 해당 여정 예약 정보가 있으면
                model.addAttribute("reservDisable","이미 예약된 여정입니다.\n메인페이지로 이동합니다.");
            }
        }
        model.addAttribute("flight",reservationService.getFlight(flight_no));
        model.addAttribute("seatAvail",seatAvail);
        model.addAttribute("seats",reservationService.getSeat(flight_no));
        model.addAttribute("airports",airportService.getAirportList());
        return "/reservation/reservationDetail";
    }

    @RequestMapping("/reservationCommit")
    public String reservationCommit(HttpServletRequest request,@RequestParam(required = false, defaultValue = "") int flight_no,
                                    @RequestParam(required = false, defaultValue = "") int seat_no, Model model) throws Exception {

        int user_no = (int) request.getSession().getAttribute("user_no");


        ArrayList<Ticket> tickets = ticketService.selectTicketInfo(user_no);
        for(Ticket ticket: tickets){
            if(ticket.getFlight_no() == flight_no){// 해당 여정 예약 정보가 있으면
                return "redirect:/";

            }
        }

        String userid = userService.findIdByNo(user_no);

        // 예약 정보 DB insert
        reservationService.reserve(user_no,seat_no);

        // 좌석정보 DB update
        reservationService.updateSeat(flight_no,seat_no);

        // 예약 번호 불러오기
        int reservatio_no = reservationService.getReservationInfo(user_no,seat_no);

        //reservationCommit에 data 넘기기
        model.addAttribute("flight",reservationService.getFlight(flight_no));
        model.addAttribute("airports",airportService.getAirportList());
        model.addAttribute("user_id",userid);
        model.addAttribute("seat_no",seat_no);
        model.addAttribute("reservation_no",reservatio_no);

        return "/reservation/reservationCommit";
    }
}
