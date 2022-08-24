package com.example.Asiana_lab.controller;

import com.example.Asiana_lab.model.dto.Flight;
import com.example.Asiana_lab.model.service.AdminService;
import com.example.Asiana_lab.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class AdminController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    //여정 추가
    @PostMapping("/add")
    public ResponseEntity<String> add(Flight flight, HttpServletRequest req) {
        adminService.addFlight(flight);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
    }

    //여정 수정
    @PutMapping("/update")
    public ResponseEntity<String> update(Flight flight) {
        if(adminService.modifyFlight(flight)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
        }
    }

    //여정 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int flight_id){
        if(adminService.deleteFlight(flight_id)){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    //여정 조회
    @GetMapping("/all")
    public ResponseEntity<List<Flight>> list() {
        List<Flight> flights = adminService.getFlightList();

        return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
    }

    //여정 선택
    @GetMapping("/{id}")
    public ResponseEntity<Flight> selectReview(@PathVariable("id") int flight_id) {
        Flight flight = adminService.getFlightById(flight_id);

        return new ResponseEntity<Flight>(flight, HttpStatus.OK);
    }
}
