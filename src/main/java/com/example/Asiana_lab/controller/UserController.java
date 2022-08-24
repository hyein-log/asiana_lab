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

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/insert")
    @ResponseBody
    public String insert() throws Exception {
        userService.join(new User(3,"123","12","12","12x", "123", 0, false));
        return "result";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, @RequestParam(required = false) String userid, @RequestParam(required = false) String password) throws Exception {
        // 로그인 인증이 된다면 "/"이동
        if(userid!=null && password!=null) {
            User user = userService.selectOneById(userid);
            if (user != null && user.getPassword().equals(password)) {
                request.getSession().setAttribute("userid", user.getUserid());
                request.getSession().setAttribute("password", user.getPassword());
                return "redirect:/";
            }
        }
        // /loginform 으로 이동
        return "/user/loginForm";
    }

    @RequestMapping("/idDuplicateCheck")
    public String idDuplicateCheck(@RequestParam(required = false) String userid){
        int user = userService.idDuplicateCheck(userid);
        return user==1? "redirect:/loginform?idDup=true" : "redirect:?loginform?isDup=false";
    }

        /*@RequestMapping("/userUpdate")
        public String userUpdate(){

        }*/
}

