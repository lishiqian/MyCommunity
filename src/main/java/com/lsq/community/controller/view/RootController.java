package com.lsq.community.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author lishiqian
 * @Date:Created in 2018/3/8 11:34
 */
@Controller
public class RootController {

    @RequestMapping("/")
    public String root(){
        return "forward:/main";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String helloTest(){
        return "hello";
    }

    @RequestMapping(value = "/main",produces = "application/json;charset=utf-8")
    public String main(){
        return "forward:forum/forum_main";
    }

    /**
     * ueditor控制器跳转
     * @return
     */
    @RequestMapping("/ueditor_controller")
    public String ueditor(){
        System.out.println("图片上传------------------------------");
        return "forward:WEB-INF/util/ueditor/jsp/controller.jsp";
    }

    @RequestMapping("/head")
    public String head(HttpSession session){
        Object loginUser = session.getAttribute("login_user");
        if(loginUser == null){
            return "head/head";
        }
        return "head/head_login_status";
    }
}
