package com.example.Asiana_lab.model.service;

import com.example.Asiana_lab.model.dao.AirportDao;
import com.example.Asiana_lab.model.dto.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AirportServiceImpl implements AirportService{

    @Autowired
    AirportDao airportDao;

    @Override
    public ArrayList<Airport> getAirportList() {
        return airportDao.selectAirportList();
    }

    @Override
    public String getAirportNameByNumber(int airport_no) {
        return  airportDao.selectNameByNumber(airport_no);
    }
}
