package com.example.Asiana_lab.model.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CommentService {
    void insertComment(Map<String, String> map);

}
