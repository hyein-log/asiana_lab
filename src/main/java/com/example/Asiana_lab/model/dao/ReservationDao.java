package com.example.Asiana_lab.model.dao;

import com.example.Asiana_lab.model.dto.Flight;
import com.example.Asiana_lab.model.dto.Reservation;
import com.example.Asiana_lab.model.dto.Seat;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface ReservationDao {
    // 모든 여정(Flight)정보 select
    ArrayList<Flight> selectAllFlight();

    // 하나의 여정 정보 select
    Flight selectOneFlight(int flight_no);

    // 해당 여정의 모든 좌석(Seat)정보 select
    ArrayList<Seat> selectAllSeat(int flight_no);

    // 한 좌석 정보 select
    Seat selectOneSeat(int flight_no,int seat_no);

    // 예약할 좌석 DB에 insert
    void insertReservation(int user_no, int seat_no);

}
