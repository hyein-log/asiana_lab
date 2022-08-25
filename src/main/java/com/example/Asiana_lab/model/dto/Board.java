package com.example.Asiana_lab.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private int board_no; //게시글 no
    private String title; //제목
    private String content; //내용
    private int cnt; //조회수
    private String reg_date; //작성일; 작성,수정시 업데이트
    private int user_no; //작성자; User table 회원번호 참조

    public Board(String title, String content, int user_no) {
        this.title = title;
        this.content = content;
        this.user_no = user_no;
    }
}
