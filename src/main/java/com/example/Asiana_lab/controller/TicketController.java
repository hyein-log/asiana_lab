package com.example.Asiana_lab.controller;

import com.example.Asiana_lab.model.dto.Ticket;
import com.example.Asiana_lab.model.service.AirportService;
import com.example.Asiana_lab.model.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    AirportService airportService;
    @RequestMapping("/reserveInfo" )
    public String reserveInfo(HttpServletRequest request, Model model){
        int user_no = (int) request.getSession().getAttribute("user_no");
        System.out.println("user_no@@@@@@@@@@@@@@@@"+user_no);
        ArrayList<Ticket> tickets = ticketService.selectTicketInfo(user_no);
        model.addAttribute("tickets", tickets);
        model.addAttribute("airports",airportService.getAirportList());
        return "/ticket/info";
    }

    @RequestMapping("/seatCancel")
    public String seatCancel(HttpServletRequest request, @RequestParam int resv_no){
        int user_no= (int) request.getSession().getAttribute("user_no");
        ArrayList<Ticket> tickets = ticketService.selectTicketInfo(user_no);
        for(Ticket ticket : tickets){
            if(ticket.getResv_no()==resv_no){
                ticketService.seatCancel(ticket);
            }
        }
        return "/";
    }
}
