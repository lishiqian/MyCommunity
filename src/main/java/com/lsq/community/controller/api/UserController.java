package com.lsq.community.controller.api;

import com.lsq.community.common.ErrorCode;
import com.lsq.community.po.User;
import com.lsq.community.service.UserService;
import com.lsq.community.util.JsonUtil;
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


    @RequestMapping(value = "/get_user_info",produces = "application/json;charset=utf-8")
    @ResponseBody()
    public String getUserInfo(Integer id){
        User user = userService.selectUserById(id);
        return ErrorCode.ok(user).toString();
    }
}
