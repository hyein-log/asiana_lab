package com.example.Asiana_lab.model.service;

import com.example.Asiana_lab.model.dto.Airport;

import java.util.ArrayList;

public interface AirportService {
    // 모든 Airport list로 가져오기
    ArrayList<Airport> getAirportList();
    //airport_no로 airport_name 가져오기
    String getAirportNameByNumber(int airport_no);
}
