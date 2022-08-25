package com.example.Asiana_lab.controller;


import com.example.Asiana_lab.model.dto.Board;
import com.example.Asiana_lab.model.dto.User;
import com.example.Asiana_lab.model.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String selectOneByNo(@RequestParam int board_no, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        Cookie[] cookies = request.getCookies();

        Cookie viewCookie = null;

        if (cookies != null && cookies.length > 0)
        {
            for (int i = 0; i < cookies.length; i++)
            {
                // Cookie의 name이 cookie + reviewNo와 일치하는 쿠키를 viewCookie에 넣어줌
                if (cookies[i].getName().equals("cookie"+board_no))
                {
                    System.out.println("처음 쿠키가 생성한 뒤 들어옴.");
                    viewCookie = cookies[i];
                }
            }
        }

        if (viewCookie == null) {
            System.out.println("cookie 없음");

            Cookie newCookie = new Cookie("cookie"+board_no, "|" + board_no + "|");

            response.addCookie(newCookie);

            boardService.addCnt(board_no);
        }
        else {
            System.out.println("cookie 있음");

            String value = viewCookie.getValue();

            System.out.println("cookie 값 : " + value);

        }


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
