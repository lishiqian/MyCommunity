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
    public RootController(){
        System.out.println("----------------------SPRING--------------------------------------------");
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String helloTest(){
        return "hello";
    }
}
