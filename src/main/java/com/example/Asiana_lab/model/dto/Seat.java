package com.example.Asiana_lab.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    private int seat_no; //좌석번호
    private String booking_class; //좌석등급
    private String isAvailable; //예약가능여부
    private int price; //가격
    private int flight_no; //여정번호
}
