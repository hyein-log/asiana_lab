package com.example.Asiana_lab.controller;


import com.example.Asiana_lab.model.dto.Board;
import com.example.Asiana_lab.model.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @RequestMapping("/boardList")
    public String findAll(Model model) throws Exception {

        model.addAttribute("list", boardService.findAll());

        return "/board/boardList";
    }

}
