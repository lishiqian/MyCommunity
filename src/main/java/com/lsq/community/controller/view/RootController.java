package com.lsq.community.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lishiqian
 * @Date:Created in 2018/3/8 11:34
 */
@Controller
public class RootController {
    @RequestMapping("/hello")
    @ResponseBody
    public String helloTest(){
        return "hello";
    }

    @RequestMapping(value = "/main",produces = "application/json;charset=utf-8")
    public String main(){
        return "redirect:forum/forum_list";
    }

    @RequestMapping("/ueditor_controller")
    public String ueditor(){
        System.out.println("------------------------------");
        return "forward:WEB-INF/util/ueditor/jsp/controller.jsp";
    }
}
