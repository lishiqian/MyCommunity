package com.lsq.community.controller.api;

import com.lsq.community.po.ForumComment;
import com.lsq.community.service.ForumCommentService;
import com.lsq.community.service.ForumService;
import com.lsq.community.service.impl.ForumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/forum_comment")
public class ForumCommentController {

    @Autowired
    private ForumCommentService forumCommentService;

    @Autowired
    private ForumService forumService;

    @RequestMapping("/add_forum_comment")
    public String addForumComment(ForumComment forumComment){
        //保存评论内容
        forumComment.setCreateDate(new Date());
        forumComment.setStatus(1);
        forumCommentService.addForumComment(forumComment);

        //用户评论数加1
        forumService.addCommentNum(forumComment.getForumId());

        return String.format("redirect:/forum/show_forum?forumId=%d",forumComment.getForumId());
    }

}
