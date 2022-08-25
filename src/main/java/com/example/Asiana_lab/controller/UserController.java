package com.example.Asiana_lab.controller;

import com.example.Asiana_lab.model.dto.User;
import com.example.Asiana_lab.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/insert")
    public String insert(HttpServletRequest request,@RequestParam(required = false) String userid, @RequestParam(required = false) String password, @RequestParam(required = false) String birthday
            , @RequestParam(required = false) String email, @RequestParam(required = false) String passport) throws Exception {
        userService.join(new User(userid, password,email,passport,birthday));
        return "/user/loginForm";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, @RequestParam(required = false) String userid, @RequestParam(required = false) String password)  throws Exception {
        // 로그인 인증이 된다면 "/"이동
        if(userid!=null && password!=null) {
            User user = userService.selectIdByUserid(userid);
            if (user != null && user.getPassword().equals(password)) {
                request.getSession().setAttribute("userid", user.getUserid());
                request.getSession().setAttribute("password", user.getPassword());
                return "/reservation/main";
            }
        }
        // /loginform 으로 이동
        return "/user/loginForm";
    }

    @RequestMapping("/idDuplicateCheck")
    public int idDuplicateCheck(@RequestParam(required = false) String userid){
        int user = userService.idDuplicateCheck(userid);
        System.out.println("===================>user:"+user);
        return user==1? 1:0;
    }

    @RequestMapping("/changeId")
        public String changeId(@RequestParam String userid) throws Exception {
        userService.changeId(userid);
        return "redirect:/";
    }

    @RequestMapping("/changePw")
        public String changePw(@RequestParam String userpw) throws Exception {
        userService.changePw(userpw);
        return "redirect:/";
    }
    @RequestMapping("/userdelete")
        public String userdelete(@RequestParam int user_no){
        userService.delete(user_no);
        return "/reservation/main";
    }

    @RequestMapping("/user")
        public String user(@RequestParam(required = false) boolean idDup){
        if(idDup){

        }
        return "/user/joinForm";
    }
}

