package com.example.Asiana_lab.controller;

import com.example.Asiana_lab.model.dto.Ticket;
import com.example.Asiana_lab.model.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class TicketController {
    @Autowired
    TicketService ticketService;

    @RequestMapping("/reserveInfo" )
    public String reserveInfo(HttpServletRequest request, Model model){
        int user_no = (int) request.getSession().getAttribute("user_no");
        ArrayList<Ticket> tickets = ticketService.selectTicketInfo(user_no);
        model.addAttribute("tickets", tickets);
        return "/ticket/info";
    }
}
