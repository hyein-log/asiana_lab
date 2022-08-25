package com.example.Asiana_lab.model.dao;


import com.example.Asiana_lab.model.dto.Board;
import com.example.Asiana_lab.model.dto.Comment;
import com.example.Asiana_lab.model.dto.File;
import com.example.Asiana_lab.model.dto.User;
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
    void delete(int board_no);

    //게시글수정을 위한 리뷰 하나 select
    //param: board_no
    Board selectOneByNo(int board_no);

    //유저 탈퇴시 게시글 전체 삭제 기능
    void userDelete(String userid);

    List<Comment> findAllByBoard(int board_no);

    User findUserByBoard(int user_no);

    List<File> findFilesByBoard(int board_no);

    void addCnt(int board_no);

    void deleteComment(int board_no);

    List<Comment> selectListCommenyByNo(int board_no);
}
