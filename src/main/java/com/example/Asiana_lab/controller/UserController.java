package com.example.Asiana_lab.controller;

import com.example.Asiana_lab.model.dto.User;
import com.example.Asiana_lab.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

    @Controller
    public class UserController {

        @Autowired
        UserService userService;

        @RequestMapping("/insert")
        @ResponseBody
        public String insert() throws Exception {
//            userService.join();
            return "result";
        }


    }

