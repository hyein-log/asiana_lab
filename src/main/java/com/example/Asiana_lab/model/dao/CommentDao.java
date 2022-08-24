package com.example.Asiana_lab.model.dao;

import com.example.Asiana_lab.model.dto.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CommentDao {

    ArrayList<Comment> findAll();
    void insert();
    void delete();

}
