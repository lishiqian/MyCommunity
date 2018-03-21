package com.lsq.community.controller.api;

import com.google.gson.Gson;
import com.lsq.community.pojo.User;
import com.lsq.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lishiqian
 * @Date:Created in 2018/3/8 18:13
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    @Autowired
    public Gson gson;

    @RequestMapping(value = "/get_user_info",produces = "application/json;charset=utf-8")
    @ResponseBody()
    public String getUserInfo(Long id){
        User user = userService.selectUserById(id);
        return gson.toJson(user);
    }
}
