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
import java.io.IOException;

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
                request.getSession().setAttribute("user_no", user.getUser_no());

                System.out.println("관리자 페이지 XX");

                System.out.println(user.isAdmin() + "@@@@@@@@@@@@@@@@@@@@@@");

                if(user.isAdmin()) {
                    System.out.println("관리자 페이지");
                    return "redirect:/admin/main";
                }
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

    @RequestMapping("/changeId")
    public String changeId(@RequestParam User user) throws Exception {
        userService.changeId(user);
        return "redirect:/";
    }

    @RequestMapping("/changePw")
    public String changePw(@RequestParam User user) throws Exception {
        userService.changePw(user);
        return "redirect:/";
    }
    @RequestMapping("/userdelete")
    public String userdelete(@RequestParam int user_no){
        userService.user_delete(user_no);
        return "/reservation/main";
    }

    @RequestMapping("/user")
    public String user(){
        return "/user/joinForm";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("userid");
        request.getSession().removeAttribute("password");
        request.getSession().invalidate();
        return "redirect:/";
    }

    @RequestMapping("/mypage")
    public String mypage(){
        return "/user/mypage";
    }


    @RequestMapping("/infoUpdate")
    public String infoUpdate(HttpServletRequest request, Model model){
        String userid = (String) request.getSession().getAttribute("userid");
        User user = userService.selectIdByUserid(userid);
        System.out.println("---------------user : "+user);
        model.addAttribute("user",user);
        return "/user/userUpdate";
    }

    @RequestMapping("/userDelete")
    public String userDelete(HttpServletRequest request){
        int user_no = (int) request.getSession().getAttribute("user_no");
        userService.user_delete(user_no);
        request.getSession().invalidate();
        return "/reservation/main";
    }

    @RequestMapping("/idSearch")
    public String idSearch() throws Exception {
            return "/user/idSearch";

    }

    @RequestMapping("/idSearch2")
    public String idSearch2(@RequestParam String email, Model model) throws Exception {
        User user = userService.selectOneByEmail(email);
        if(user.getUserid().length()!=0||user.getUserid()!=null) {
            model.addAttribute("userid", user.getUserid());
            return "/user/findId";
        }
        return "redirect:/";
    }

    @RequestMapping("/pwSearch")
    public String pwSearch() throws Exception {
        return "/user/pwSearch";

    }
    @RequestMapping("/pwSearch2")
    public String pwSearch2(@RequestParam String userid, Model model) throws Exception {
        String password = userService.selectIdByUserid(userid).getPassword();
        model.addAttribute("password", password);
        return "/user/findPw";

    }

     @RequestMapping("/idCheck")
    public String idCheck() throws IOException {

         return "/user/idCheck";

    }@RequestMapping("/idCheckProc")
    public String idCheckProc(HttpServletRequest request, @RequestParam String userid) throws IOException {
        int dup = userService.idDuplicateCheck(userid);
        if(dup==0){
            request.getSession().setAttribute("userid", userid);
            return "redirect:/user";
        }
         return "redirect:/user";
    }
    @RequestMapping("/emailCheck")
    public String emailCheck() throws IOException {

        return "/user/emailCheck";
    }
    @RequestMapping("/emailCheckProc")
    public String emailCheckProc(HttpServletRequest request, @RequestParam String email) throws IOException {
        int dup = userService.emailDuplicateCheck(email);
        if(dup==0){
            request.getSession().setAttribute("email", email);
            return "redirect:/";
        }
        return "redirect:/";
    }

    @RequestMapping("/emailUpdate")
    public String userUpdate(HttpServletRequest request,@RequestParam String email, Model model) throws Exception {
        int user_no = (int) request.getSession().getAttribute("user_no");
        System.out.println(email+"+email**************888");
        System.out.println(user_no+"+user_no**************888");
        User user = userService.selectOneById(user_no);
        user.setEmail(email);
        userService.updateEmail(user);
        model.addAttribute("user", user);
        return "redirect:/";
    }
    @RequestMapping("/pwUpdate")
    @ResponseBody
    public String pwUpdate(HttpServletRequest request, @RequestParam String password) throws Exception {
        int user_no = (int) request.getSession().getAttribute("user_no");
        User user = userService.selectOneById(user_no);

        user.setPassword(password);
        userService.updatePw(user);
        return "redirect:/";
    }


}

