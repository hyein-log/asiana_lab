package com.example.Asiana_lab.model.service;

import com.example.Asiana_lab.model.dto.Flight;
import com.example.Asiana_lab.model.dto.Seat;

import java.util.ArrayList;
import java.util.List;

public interface ReservationService {
    // 여정정보 전체 조회
    List<Flight> getFlightList();
    // 특정 여정 정보 조회
    Flight getFlight(int flight_no);
    // 해당 여정 좌석 조회
    ArrayList<Seat> getSeat(int flight_no);
    // 예약 하기
    void reserve(int flight_no,int seat_no);

}
