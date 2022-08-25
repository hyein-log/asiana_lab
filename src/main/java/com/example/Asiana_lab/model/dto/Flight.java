package com.example.Asiana_lab.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    private int flight_no; //여정번호
    private String dep_time; // 출발일+시간
    private int departure; //출발지 공항 번호
    private int destination; //도착지 공항 번호
}
