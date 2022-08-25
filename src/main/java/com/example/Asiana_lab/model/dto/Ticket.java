package com.example.Asiana_lab.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private int user_no; //회원번호
    private int flight_no; //여정번호
    private String dep_time; // 출발일+시간
    private int departure; //출발지 공항 번호
    private int destination; //도착지 공항 번호
    private int seat_no; //좌석번호
    private String booking_class; //좌석등급
    private String isAvailable; //예약가능여부
    private int price; //가격
    private int resv_no; //예약번호
}
