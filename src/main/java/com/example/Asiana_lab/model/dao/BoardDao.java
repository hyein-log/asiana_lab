package com.example.Asiana_lab.model.dao;


import com.example.Asiana_lab.model.dto.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {

    List<Board> findAll();

    //게시글 추가
    void insertBoard(Board board);

    //게시글 수정
    int updateBoard(Board board);

    //게시글 삭제
    //param: board_no
    int deleteBoard(int no);

    //게시글수정을 위한 리뷰 하나 select
    //param: board_no
    Board selectOne(int id);

    //유저 탈퇴시 게시글 전체 삭제 기능
    void userDelete(String userid);
}
