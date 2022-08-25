package com.example.Asiana_lab.model.service;

import com.example.Asiana_lab.model.dao.TicketDao;
import com.example.Asiana_lab.model.dto.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    private TicketDao ticketDao;


    @Override
    public ArrayList<Ticket> selectTicketInfo(int user_no) {
        ArrayList<Ticket> tickets = ticketDao.selectTicketInfo(user_no);
        return tickets;
    }
}
