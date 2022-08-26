package com.example.Asiana_lab.model.dao;

import com.example.Asiana_lab.model.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    //회원가입
    void insert(User user);
    //회원정보수정
    void changeId(User user);
    void changePw(User user);
    //회원탈퇴
    void user_delete(int user_no);
    //아이디로 유저 찾기
    User selectOneById(int user_no);
    //이메일로 유저 찾기
    User selectOneByEmail(String email);
    //유저아이디로 id 찾기
    User selectIdByUserid(String userid);
    //아이디 중복 체크
    int idDuplicateCheck(String userid);
    //이메일 중복 체크
    int emailDuplicateCheck(String email);

    void updatePw(User user);

    void updateEmail(User user);
}
