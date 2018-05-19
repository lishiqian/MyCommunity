package com.lsq.community.controller.api;

import com.lsq.community.common.ErrorCode;
import com.lsq.community.po.Forum;
import com.lsq.community.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    public ForumService forumService;


    //http://localhost:8080/forum/add_forum_view
    @RequestMapping(value = "/add_forum_view",produces = "application/json;charset=utf-8")
    public String addForumView(){
        return "forum/eidt_forum";
    }

    @RequestMapping(value = "/add_forum",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addForum(Forum forum){
        forum.setCreateTime(new Date());
        forum.setLastUpdateTime(new Date());
        forum.setUserId(1);
        forum.setComments(0);
        forum.setReadingNum(0);
        forumService.addForum(forum);
        return ErrorCode.ok(null).toString();
    }


    @RequestMapping(value = "/forum_list",produces = "application/json;charset=utf-8")
    public String forumList(@RequestParam(value = "userId",defaultValue = "1") Integer userId, Model model){
        List<Forum> forums = forumService.selectForumsByUserId(userId);
        model.addAttribute("forums",forums);
        return "forum/forum_list";
    }

    @RequestMapping(value = "/show_forum",produces = "application/json;charset=utf-8")
    public String showForum(int forumId,Model model){
        //根据帖子id获取帖子内容
        Forum forum = forumService.selectForumsById(forumId);
        model.addAttribute("forum",forum);
        //帖子阅读数增加1
        forumService.addReaderNum(forumId);

        return "forum/show_forum";
    }
}
