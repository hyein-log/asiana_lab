package com.example.Asiana_lab.controller;


import com.example.Asiana_lab.model.dto.Board;
import com.example.Asiana_lab.model.dto.User;
import com.example.Asiana_lab.model.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/read")
    public String selectOneByNo(@RequestParam int board_no, Model model) throws Exception {

        Board board = boardService.selectOneByNo(board_no);
        model.addAttribute("board", board);

        int user_no = board.getUser_no();
        User user = boardService.findUserByBoard(user_no);

        model.addAttribute("commentByBoard", boardService.findAllByBoard(board_no));
        model.addAttribute("user", user);
        model.addAttribute("files", boardService.findFilesByBoard(board_no));

        return "/board/boardDetail";
    }

}
