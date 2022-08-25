package com.example.Asiana_lab.model.service;

import com.example.Asiana_lab.model.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentDao commentDao;

    @Override
    public void insertComment(Map<String, String> map) {
        commentDao.insertComment(map);
    }
}
