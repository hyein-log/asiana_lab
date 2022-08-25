package com.example.Asiana_lab.model.service;


import com.example.Asiana_lab.model.dao.BoardDao;
import com.example.Asiana_lab.model.dao.UserDao;
import com.example.Asiana_lab.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BoardDao boardDao;

    @Override
    public int idDuplicateCheck(String userid) {
        User user = userDao.selectIdByUserid(userid);
        return user !=null ? 1 :0;
    }

    @Override
    public int emailDuplicateCheck(String email) {
        User user = userDao.selectOneByEmail(email);
        return user !=null ? 1 :0;
    }

    @Override
    public void join(User user) throws Exception {
        userDao.insert(user);
    }


    @Override
    public String login(HttpServletRequest request, @RequestParam String userid, @RequestParam String password) throws Exception {
        User user = userDao.selectIdByUserid(userid);
        if(user!=null && user.getPassword().equals(password)){
            request.getSession().setAttribute("userid", userid);
            return "home";
        }
        return "redirect:/login";
    }

    @Override
    public String findId(String email) throws Exception {
        User user = userDao.selectOneByEmail(email);
        String userid = user.getUserid();
        return "redirect:/?userid="+userid;
    }



    @Override
    public String signOut(HttpServletRequest request,String userid) throws Exception {
        request.getSession().removeAttribute("userid");

        return "redirect:/";
    }

    @Override
    public User selectOneById(int user_no) throws Exception {
        return null;
    }

    @Override
    public User selectIdByUserid(String userid) {
        User user = userDao.selectIdByUserid(userid);
        if(userDao.selectIdByUserid(userid)!=null){
            return user;
        }
        return null;
    }

    @Override
    public void user_delete(int user_no) {
        userDao.user_delete(user_no);
    }

    @Override
    public void changeId(String password) throws Exception {
        userDao.changeId(password);
    }

    @Override
    public void changePw(String password) throws Exception {
        userDao.changePw(password);
    }

    @Override
    public String findIdByNo(int user_no) throws Exception {
        User user = userDao.selectOneById(user_no);
        String userid = user.getUserid();
        return userid;
    }



}
