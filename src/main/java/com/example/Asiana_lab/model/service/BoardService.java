package com.example.Asiana_lab.model.service;

import com.example.Asiana_lab.model.dto.Board;

import java.util.List;

public interface BoardService {

    List<Board> findAll() throws Exception;
}
