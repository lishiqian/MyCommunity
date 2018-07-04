package com.lsq.community.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsq.community.common.ErrorCode;
import com.lsq.community.common.PageData;
import com.lsq.community.custom.ForumCommentCustom;
import com.lsq.community.custom.ForumUserCustom;
import com.lsq.community.po.Forum;
import com.lsq.community.po.User;
import com.lsq.community.service.ForumCommentService;
import com.lsq.community.service.ForumService;
import com.lsq.community.util.JsonUtil;
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
    public String forumList(
            @RequestParam(value = "status",defaultValue = "1") Integer status,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            HttpSession session, Model model){
        //从session中取出登录的用户id
        User user = (User) session.getAttribute("login_user");
        if(user == null){
            return  "redirect:/main?open_login=ture";
        }
        Integer userId = user.getId();
        PageHelper.startPage(pageNum,pageSize);
        List<Forum> forums = forumService.selectForumsByUserIdAndStatus(userId,status);
        PageInfo pageInfo = new PageInfo(forums);

        System.out.println(JsonUtil.toJson(pageInfo));

        model.addAttribute("forums",forums);
        model.addAttribute("pageNum",pageInfo.getPageNum());
        model.addAttribute("pages",pageInfo.getPages());
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
    public String showForum(int forumId,Model model,HttpSession session){
        //根据帖子id获取帖子内容
        Forum forum = forumService.selectForumsById(forumId);

        //获取评论内容
        List<ForumCommentCustom> forumCommentCustoms = forumCommentService.selectForumCommentsByForunId(forumId);

        User user = (User) session.getAttribute("login_user");
        if(user == null){
            //帖子阅读数增加1
            forumService.addReaderNum(forumId);
        }else{
            Integer userId = user.getId();
            forumService.addReaderNum(forumId,userId);
        }


        model.addAttribute("forum",forum);
        model.addAttribute("forumComments",forumCommentCustoms);

        return "forum/forum_show";
    }

    /**
     * 论坛主页
     * @return
     */
    @RequestMapping("/forum_main")
    public String forumMain(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String keyword,
            Model model){

        if(keyword != null && !"".equals(keyword.trim())){
            keyword = keyword.trim();
            model.addAttribute("keyword",keyword);
        }
        PageData pageData = forumService.search(pageNum,pageSize,keyword);
        List<ForumUserCustom> forumUserCustoms = (List<ForumUserCustom>) pageData.getData();
        model.addAttribute("forumUserCustoms",forumUserCustoms);
        model.addAttribute("pages",pageData.getPageTotalNum());
        model.addAttribute("pageNum",pageData.getPageCurrentNum());
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
        session.setAttribute("lay_msg","删除成功");
        return "redirect:/forum/forum_list?status=3";
    }

    /**
     * 评论管理帖子列表
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("forum_comment_manager_list")
    public String forumCommentManagerList(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            HttpSession session,Model model){

        //从session中取出登录的用户id
        User user = (User) session.getAttribute("login_user");
        if(user == null){
            return  "redirect:/main?open_login=ture";
        }
        Integer userId = user.getId();
        PageHelper.startPage(pageNum,pageSize);
        List<Forum> forums = forumService.selectForumsByUserIdAndStatus(userId,1);
        PageInfo pageInfo = new PageInfo(forums);

        model.addAttribute("forums",forums);
        model.addAttribute("pageNum",pageInfo.getPageNum());
        model.addAttribute("pages",pageInfo.getPages());

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
