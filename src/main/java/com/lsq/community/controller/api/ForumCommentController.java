package com.lsq.community.controller.api;

import com.lsq.community.common.ErrorCode;
import com.lsq.community.po.ForumComment;
import com.lsq.community.po.User;
import com.lsq.community.service.ForumCommentService;
import com.lsq.community.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/forum_comment")
public class ForumCommentController {

    @Autowired
    private ForumCommentService forumCommentService;

    @Autowired
    private ForumService forumService;

    @RequestMapping(value = "/add_forum_comment",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addForumComment(ForumComment forumComment, HttpSession session){
        //如果用户未登录或者用户登录id和创建登录id不同
        User user = (User) session.getAttribute("login_user");
        if(user == null){
            return ErrorCode.bulid(201,"用户未登录",null).toString();
        }
        //保存评论内容
        forumComment.setCreateUserId(user.getId());
        forumComment.setCreateDate(new Date());
        forumComment.setStatus(1);
        forumCommentService.addForumComment(forumComment);

        //用户评论数加1
        forumService.addCommentNum(forumComment.getForumId());

        return ErrorCode.ok().toString();
    }

}
