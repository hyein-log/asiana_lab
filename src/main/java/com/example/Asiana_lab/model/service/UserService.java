package com.example.Asiana_lab.model.service;

import com.example.Asiana_lab.model.dto.User;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public interface UserService  {
    //아이디 중복 체크
    int idDuplicateCheck(String userid);
    //이메일 중복 체크
    int emailDuplicateCheck(String email);
    //회원가입
    void join(User user) throws Exception;
    //로그인
    String login(HttpServletRequest request, @RequestParam String userid, @RequestParam String password) throws Exception;

    //회원정보 수정
    void changeId(String password) throws Exception;
    //비밀번호 재설정
    void changePw(String userid) throws Exception;

    //아이디 찾기
    String findId(String email) throws Exception;

    //회원 탈퇴
    String signOut(HttpServletRequest request, String userid) throws Exception;
    //아이디로 회원 불러오기
    User selectOneById(String userid) throws Exception;

    //회원 번호로 아이디 불러오기
    String findIdByNo(int user_no) throws Exception;

}
