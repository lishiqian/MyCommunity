package com.lsq.community.controller.view;

import com.lsq.community.common.ErrorCode;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
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

    private Logger logger = Logger.getLogger(RootController.class);

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
    @RequestMapping(value = "/ueditor_controller")
    public String ueditor(){
        logger.info("图片上传------------------------------");
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


    @RequestMapping(value = "/show_toast",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showToast(HttpSession session){
        Object lay_msg = session.getAttribute("lay_msg");
        if(lay_msg == null){
            return ErrorCode.bulid(201,null,null).toString();
        }

        session.removeAttribute("lay_msg");
        return  ErrorCode.ok(lay_msg).toString();
    }
}
