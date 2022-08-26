package com.example.Asiana_lab.model.service;

import com.example.Asiana_lab.model.dto.Ticket;

import java.util.ArrayList;

public interface TicketService {

    ArrayList<Ticket>  selectTicketInfo(int user_no);

    void seatCancel(Ticket ticket);

}
