package com.example.Asiana_lab.model.service;

import com.example.Asiana_lab.model.dto.Board;
import com.example.Asiana_lab.model.dto.Comment;
import com.example.Asiana_lab.model.dto.File;
import com.example.Asiana_lab.model.dto.User;

import java.util.List;

public interface BoardService {

    List<Board> findAll() throws Exception;

    Board selectOneByNo(int board_no);

    List<Comment> findAllByBoard(int board_no);

    User findUserByBoard(int user_no);

    List<File> findFilesByBoard(int board_no);
}
