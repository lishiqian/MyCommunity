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
        return "forum/forum_edit_add";
    }

    @RequestMapping(value = "/add_forum",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addForum(Forum forum,HttpSession session){
        forum.setCreateTime(new Date());
        forum.setLastUpdateTime(new Date());
        forum.setComments(0);
        forum.setReadingNum(0);

        User loginUser = (User) session.getAttribute("login_user");
        forum.setUserId(loginUser.getId());

        forumService.addForum(forum);
        return ErrorCode.ok(null).toString();
    }

    /**
     * 展示帖子修改页面
     * @param forumId
     * @param model
     * @return
     */
    @RequestMapping("/edit_forum_view")
    public String editForumView(Integer forumId,Model model){
        Forum forum = forumService.selectForumsById(forumId);
        model.addAttribute("forum",forum);

        return "forum/forum_edit_update";
    }


    /**
     * 修改帖子
     * @return
     */
    @RequestMapping("/update_forum")
    public String updateForum(Forum forum){
        forum.setLastUpdateTime(new Date());
        forumService.updateForum(forum);

        if(forum.getStatus().equals(1)){
            return "redirect:/forum/forum_list?status=1";
        }else {
            return "redirect:/forum/forum_list?status=2";
        }
    }

    /**
     * 帖子列表展示公用接口
     * @param status
     * @param session
     * @param model
     * @return
     */
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
        if(status == 1)
            return "forum/forum_list";
        else if(status == 2)
            return "forum/forum_list_draft";
        else
            return "forum/forum_list_grabage";
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

        return "forum/forum_show";
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

    /**
     * 帖子删除
     * @param userId
     * @param forumId
     * @return
     */
    @RequestMapping("/forum_delete")
    public String forumDelete(Integer userId,Integer forumId,HttpSession session){
        User loginUser = (User) session.getAttribute("login_user");
        if(loginUser.getId().equals(userId)){
            forumService.deleteForum(forumId);
        }

        return "redirect:/forum/forum_list?status=3&lay_msg=delete_success";
    }

    /**
     * 评论管理帖子列表
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("forum_comment_manager_list")
    public String forumCommentManagerList(HttpSession session,Model model){
        //从session中取出登录的用户id
        User user = (User) session.getAttribute("login_user");
        if(user == null){
            return  "redirect:/main?open_login=ture";
        }
        Integer userId = user.getId();

        List<Forum> forums = forumService.selectForumsByUserIdAndStatus(userId,1);
        model.addAttribute("forums",forums);
        return "forum/forum_comment_manager_list";
    }

    @RequestMapping("forum_comment_manager_show")
    public String forumCommentManagerShow(Integer forumId,Model model){
        //根据帖子id获取帖子内容
        Forum forum = forumService.selectForumsById(forumId);

        //获取评论内容
        List<ForumCommentCustom> forumCommentCustoms = forumCommentService.selectForumCommentsManagerByForunId(forumId);

        //帖子阅读数增加1
        forumService.addReaderNum(forumId);

        model.addAttribute("forum",forum);
        model.addAttribute("forumComments",forumCommentCustoms);
        return "forum/forum_comment_manager";
    }
}
