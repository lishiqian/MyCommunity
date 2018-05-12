package com.lsq.community.controller.api;

import com.lsq.community.common.ErrorCode;
import com.lsq.community.po.Forum;
import com.lsq.community.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    public ForumService forumService;


    //http://localhost:8080/forum/add_forum_view
    @RequestMapping(value = "/add_forum_view",produces = "application/json;charset=utf-8")
    public String addForumView(){
        return "bbs-edit";
    }

    @RequestMapping(value = "/add_forum",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addForum(Forum forum){
        forum.setCreateTime(new Date());
        forum.setLastUpdateTime(new Date());
        forum.setUserId(1);
        forumService.addForum(forum);
        return ErrorCode.ok(null).toString();
    }
}
