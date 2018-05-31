package com.lsq.community.controller.api;

import com.lsq.community.common.ErrorCode;
import com.lsq.community.custom.ForumCommentCustom;
import com.lsq.community.custom.ForumUserCustom;
import com.lsq.community.po.Forum;
import com.lsq.community.po.User;
import com.lsq.community.service.ForumCommentService;
import com.lsq.community.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    public ForumService forumService;

    @Autowired
    private ForumCommentService forumCommentService;


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
    public String forumList(@RequestParam(value = "status",defaultValue = "1") Integer status,HttpSession session, Model model){
        //从session中取出登录的用户id
        User user = (User) session.getAttribute("login_user");
        if(user == null){
            return  "redirect:/main?open_login=ture";
        }
        Integer userId = user.getId();

        List<Forum> forums = forumService.selectForumsByUserIdAndStatus(userId,status);
        model.addAttribute("forums",forums);
        model.addAttribute("status",status);
        return "forum/forum_list";
    }

    /**
     * 帖子展示
     * @param forumId
     * @param model
     * @return
     */
    @RequestMapping(value = "/show_forum",produces = "application/json;charset=utf-8")
    public String showForum(int forumId,Model model){
        //根据帖子id获取帖子内容
        Forum forum = forumService.selectForumsById(forumId);

        //获取评论内容
        List<ForumCommentCustom> forumCommentCustoms = forumCommentService.selectForumCommentsByForunId(forumId);

        //帖子阅读数增加1
        forumService.addReaderNum(forumId);

        model.addAttribute("forum",forum);
        model.addAttribute("forumComments",forumCommentCustoms);

        return "forum/show_forum";
    }

    /**
     * 论坛主页
     * @return
     */
    @RequestMapping("/forum_main")
    public String forumMain(Model model){
        List<ForumUserCustom> forumUserCustoms = forumService.selectForumsOrderByReadingNum();
        model.addAttribute("forumUserCustoms",forumUserCustoms);
        return "main";
    }
}
