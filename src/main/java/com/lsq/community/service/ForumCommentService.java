package com.lsq.community.service;

import com.lsq.community.custom.ForumCommentCustom;
import com.lsq.community.po.ForumComment;

import java.util.List;

public interface ForumCommentService {
    void addForumComment(ForumComment forumComment);

    /**
     * 获取帖子评论内容（包括评论用户信息）
     * @param forumId
     * @return
     */
    List<ForumCommentCustom> selectForumCommentsByForunId(Integer forumId);

    /**
     * 获取status的状态为1和2的所有评论
     * @param forumId
     * @return
     */
    List<ForumCommentCustom> selectForumCommentsManagerByForunId(Integer forumId);

    /**
     * 修改用户评论内容
     * @param commentId
     * @param status
     */
    void updateForumCommentStatus(Integer commentId,Integer status);
}
