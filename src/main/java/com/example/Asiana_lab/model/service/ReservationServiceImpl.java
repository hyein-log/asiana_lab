package com.example.Asiana_lab.model.service;

import com.example.Asiana_lab.model.dao.ReservationDao;
import com.example.Asiana_lab.model.dto.Flight;
import com.example.Asiana_lab.model.dto.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

   @Autowired
   ReservationDao reservationDao;

    // 여정정보 전체 조회
    @Override
    public List<Flight> getFlightList() {
        return reservationDao.selectAllFlight();
    }
    // 특정 여정 정보 조회
    @Override
    public Flight getFlight(int flight_no) {
        return reservationDao.selectOneFlight(flight_no);
    }
    // 해당 여정 좌석 조회
    @Override
    public ArrayList<Seat> getSeat(int flight_no) {
        return reservationDao.selectAllSeat(flight_no);
    }
    // 특정 좌석 예약 가능여부
    @Override
    public boolean getIsAvailable(int flight_no, int seat_no) {
        Seat seat = reservationDao.selectOneSeat(flight_no,seat_no);
        if(seat.isAvailable()){
            return true;
        }
        return false;
    }

    // 예약 하기
    @Override
    public void reserve(int flight_no, int seat_no) {
        reservationDao.insertReservation(flight_no,seat_no);
    }
}
