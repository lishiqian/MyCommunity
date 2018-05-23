package com.lsq.community.controller.api;

import com.lsq.community.common.ErrorCode;
import com.lsq.community.po.User;
import com.lsq.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/show_login")
    public String showLogin(){
        return "user/login";
    }

    @RequestMapping(value = "/login",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(String username, String passwd, HttpSession session){
        User user = userService.selectUsername(username);
        if(user == null){
            return ErrorCode.bulid(201,"用户名不存在",null).toString();
        }else if(!user.getPassword().equals(passwd)){
            return ErrorCode.bulid(202,"密码错误",null).toString();
        }
        session.setAttribute("login_user",user);
        return ErrorCode.ok(null).toString();
    }


    @RequestMapping("/show_register")
    public String showRegister(){
        return "user/register";
    }


}
