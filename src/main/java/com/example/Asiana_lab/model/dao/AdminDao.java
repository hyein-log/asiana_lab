package com.example.Asiana_lab.model.dao;

import com.example.Asiana_lab.model.dto.Flight;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {

    //여정 정보 추가 -> 자동 좌석 정보 추가
    void insertFlight(Flight flight);

    //여정 정보 삭제
    int deleteFlight(int flight_no);

    //여정 정보 수정
    int updateFlight(Flight flight);

    //여정 조회시 택1
    Flight selectOne(int flight_no);
    
    //회원 탈퇴
    void deleteUser(int user_no);


}
