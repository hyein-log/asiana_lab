package com.example.Asiana_lab.model.service;

import com.example.Asiana_lab.model.dao.BoardDao;
import com.example.Asiana_lab.model.dto.Board;
import com.example.Asiana_lab.model.dto.Comment;
import com.example.Asiana_lab.model.dto.File;
import com.example.Asiana_lab.model.dto.User;
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

    @Override
    public Board selectOneByNo(int board_no) {

        return boardDao.selectOneByNo(board_no);
    }

    @Override
    public List<Comment> findAllByBoard(int board_no) {

        return boardDao.findAllByBoard(board_no);
    }

    @Override
    public User findUserByBoard(int user_no) {
        return boardDao.findUserByBoard(user_no);
    }

    @Override
    public List<File> findFilesByBoard(int board_no) {
        return boardDao.findFilesByBoard(board_no);
    }

    @Override
    public void addCnt(int board_no) {
        boardDao.addCnt(board_no);
    }

    @Override
    public void updateBoard(Board board) {
        boardDao.updateBoard(board);
    }

    @Override
    public void insertBoard(Board board) {
        boardDao.insertBoard(board);
    }

    @Override
    public void delete(int board_no) {
        boardDao.delete(board_no);
    }

    @Override
    public List<Comment> selectListCommentByNo(int board_no) {
        return boardDao.selectListCommenyByNo(board_no);
    }

    @Override
    public void deleteComment(int board_no) {
        boardDao.deleteComment(board_no);
    }
}
