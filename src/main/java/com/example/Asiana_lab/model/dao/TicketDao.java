package com.example.Asiana_lab.model.dao;

import com.example.Asiana_lab.model.dto.Ticket;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TicketDao {
    ArrayList<Ticket> selectTicketInfo(int user_no);
    void seatCancel(Ticket ticket);
    void seatIsAvailable(Ticket ticket);
}
