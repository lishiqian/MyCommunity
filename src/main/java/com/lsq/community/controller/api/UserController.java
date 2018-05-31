package com.lsq.community.controller.api;

import com.lsq.community.common.ErrorCode;
import com.lsq.community.po.User;
import com.lsq.community.service.UserService;
import com.lsq.community.util.CommonUtil;
import com.lsq.community.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author lishiqian
 * @Date:Created in 2018/3/8 18:13
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    public UserService userService;


    @RequestMapping(value = "/get_user_info",produces = "application/json;charset=utf-8")
    @ResponseBody()
    public String getUserInfo(Integer id){
        User user = userService.selectUserById(id);
        return ErrorCode.ok(user).toString();
    }

    @RequestMapping("/show_user")
    public String showUser(HttpSession session, Model model){
        User loginUser = (User) session.getAttribute("login_user");
        model.addAttribute("userGender",CommonUtil.getUserGenderString(loginUser.getGender()));
        return "user/show_user";
    }

    @RequestMapping("/edit_user_view")
    public String editUserView(){
        return "user/edit_user";
    }


    @RequestMapping("/edit_user")
    public String editUser(User user,HttpSession session){
        User loginUser = (User) session.getAttribute("login_user");
        loginUser.setUsername(user.getUsername());
        loginUser.setGender(user.getGender());
        loginUser.setLastUpdateTime(new Date());

        userService.updateUser(loginUser);
        logger.info("update user info success !!!");

        return "redirect:/user/show_user";
    }


    @RequestMapping(value = "/head_img_upload",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String headImgUpload(MultipartFile uploadFile, HttpSession session) throws IOException {
        //获取文件保存路径
        String realPath = session.getServletContext().getRealPath("/") + "WEB-INF/";
        String originalFilename = uploadFile.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String fileName = "img/" + CommonUtil.getCurrentDateFileName() + extName;
        //保存文件到服务器
        uploadFile.transferTo( new File(realPath,fileName));
        //修改用户文件到数据库
        User loginUser = (User) session.getAttribute("login_user");
        userService.updateUserImg(loginUser.getId(),fileName);
        //更改session中user的头像
        loginUser.setHeadImg(fileName);

        logger.info("file upload success !!!");

        return ErrorCode.ok(fileName).toString();
    }
}
