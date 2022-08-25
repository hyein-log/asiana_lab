package com.example.Asiana_lab.model.dao;

import com.example.Asiana_lab.model.dto.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface CommentDao {

    ArrayList<Comment> findAll();
    void insertComment(Map<String, String> map);
    void delete();

}
