package com.example.Asiana_lab.model.service;

import com.example.Asiana_lab.model.dao.AdminDao;
import com.example.Asiana_lab.model.dao.ReservationDao;
import com.example.Asiana_lab.model.dao.UserDao;
import com.example.Asiana_lab.model.dto.Flight;
import com.example.Asiana_lab.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private UserDao userDao;

    //여정추가
    @Override
    public void addFlight(Flight flight) {
        adminDao.insertFlight(flight);
    }

    //여정수정
    @Override
    public boolean modifyFlight(Flight flight) {
        Flight originFlight = adminDao.selectOne((flight.getFlight_no()));

        originFlight.setDep_time(flight.getDep_time());
        originFlight.setDeparture(flight.getDeparture());
        originFlight.setDestination(flight.getDestination());

        return adminDao.updateFlight(originFlight) == 1;
    }

    //여정삭제
    @Override
    public boolean deleteFlight(int flight_no) {

        return adminDao.deleteFlight(flight_no) == 1;
    }

    //전체 여정조회
    @Override
    public List<Flight> getFlightList() {
        return adminDao.selectAllFlight();
    }

    //여정 아이디 얻어오기
    @Override
    public Flight getFlightById(int flight_no) {
        return adminDao.selectOne(flight_no);
    }

    //여정 읽기
    @Override
    public Flight readFlight(int flight_no) {
        return adminDao.selectOne(flight_no);
    }

    @Override
    public boolean checkAdmin(@RequestParam String userid) {
        User user = userDao.selectIdByUserid(userid);
        if(user.isAdmin()){
            return true;
        }
        return false;
    }
}
