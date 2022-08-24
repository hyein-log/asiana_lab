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
            System.out.println("Controller");
            userService.join(new User(2, "123", "123", "123", "123", "123", 100, true));
            return "result";
        }

        @RequestMapping("/")
        @ResponseBody
        public String main() throws Exception {
            return "index";
        }
    }

