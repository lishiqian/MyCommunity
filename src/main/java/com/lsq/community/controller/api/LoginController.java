package com.lsq.community.controller.api;

import com.lsq.community.common.ErrorCode;
import com.lsq.community.po.User;
import com.lsq.community.service.UserService;
import com.lsq.community.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

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
    public String login(String email, String passwd, HttpSession session){
        User user = userService.selectUsername(email);
        if(user == null){
            return ErrorCode.bulid(201,"该邮箱未注册",null).toString();
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

    @RequestMapping(value = "/register",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String register(String email,String username,String passwd,HttpServletRequest request){
        //判断email是否能使用
        boolean exsit = userService.selectEmailExsit(email);
        if(exsit)
            return ErrorCode.bulid(201,"Email已经存在",null).toString();

        //保存用户信息
        String ip = CommonUtil.getClientIp(request); //获取注册ip
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwd);
        user.setRegIp(ip);
        user.setRegTime(new Date());
        user.setLastUpdateTime(new Date());
        user.setStatus(0);
        //保存到数据库
        userService.addUser(user);

        return ErrorCode.ok(null).toString();
    }



    @RequestMapping("clean_user")
    public String cleanUser(HttpSession session){
        session.removeAttribute("login_user");
        return "redirect:/main";
    }



}
