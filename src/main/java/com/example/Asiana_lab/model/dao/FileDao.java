package com.example.Asiana_lab.model.dao;

import com.example.Asiana_lab.model.dto.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface FileDao {

    ArrayList<File> findAll();
    void insert();
    File update(int file_no);
}
