package com.example.Asiana_lab.model.dao;

import com.example.Asiana_lab.model.dto.Airport;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface AirportDao {
    //모든 airport list select
    ArrayList<Airport> selectAirportList();
    //airport_no로 airport_name select
    String selectNameByNumber(int airport_no);
}
