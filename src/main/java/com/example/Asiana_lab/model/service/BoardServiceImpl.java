package com.example.Asiana_lab.model.service;

import com.example.Asiana_lab.model.dao.BoardDao;
import com.example.Asiana_lab.model.dto.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDao boardDao;

    @Override
    public List<Board> findAll() throws Exception {

        return boardDao.findAll();
    }
}
