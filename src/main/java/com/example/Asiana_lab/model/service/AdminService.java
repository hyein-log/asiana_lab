package com.example.Asiana_lab.model.service;

import com.example.Asiana_lab.model.dto.Flight;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    /* 여정 관리 */
    //여정추가
    void addFlight(Flight flight);

    //여정수정
    boolean modifyFlight(Flight flight);
    
    //여정삭제
    boolean deleteFlight(int flight_no);
    
    //전체 여정
    List<Flight> getFlightList();
    
    //여정 아이디 얻어오기
    Flight getFlightById(int flight_no);
    
    //여정읽기
    Flight readFlight(int flight_no);

    /* 회원 관리 */
}
