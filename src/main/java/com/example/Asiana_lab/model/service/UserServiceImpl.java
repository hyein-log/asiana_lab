package com.example.Asiana_lab.model.service;


import com.example.Asiana_lab.model.dao.BoardDao;
import com.example.Asiana_lab.model.dao.UserDao;
import com.example.Asiana_lab.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BoardDao boardDao;

    @Override
    public int idDuplicateCheck(String userid) {
        return 0;
    }

    @Override
    public int emailDuplicateCheck(String email) {
        return 0;
    }

    @Override
    public void join(User user) throws Exception {
        userDao.insert(user);
    }

    @Override
    public int login(String userId, String pw) throws Exception {
        return 0;
    }

    @Override
    public void changeUserInfo(User newUser, String userid) {

    }

    @Override
    public String findId(String email) throws Exception {
        return null;
    }

    @Override
    public void changePw(String userid, String newPw) throws Exception {

    }

    @Override
    public int signOut(String userid) throws Exception {
        return 0;
    }

    @Override
    public User selectOneById(String userid) throws Exception {
        return null;
    }
}
