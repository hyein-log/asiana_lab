package com.example.Asiana_lab.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    private int airport_no;
    private String airport_code;
    private String airport_name;
}
