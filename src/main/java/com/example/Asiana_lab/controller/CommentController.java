package com.example.Asiana_lab.controller;

import com.example.Asiana_lab.model.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("comment/insert")
    public String insert(@RequestParam String content, @RequestParam int board_no,
                         @RequestParam String userid, Model model) {

        Map<String, String> map = new HashMap<>();
        map.put("content", content);
        map.put("board_no", String.valueOf(board_no));

        commentService.insertComment(map);

        return "redirect:/read?board_no=" + board_no;
    }
}
