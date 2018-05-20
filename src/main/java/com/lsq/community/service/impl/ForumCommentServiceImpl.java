package com.lsq.community.service.impl;

import com.lsq.community.mapper.ForumCommentMapper;
import com.lsq.community.mapper.ForumMapper;
import com.lsq.community.po.ForumComment;
import com.lsq.community.service.ForumCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("forumCommentService")
public class ForumCommentServiceImpl implements ForumCommentService {

    @Autowired
    private ForumCommentMapper forumCommentMapper;


    /**
     * 添加用户对帖子的评论内容
     * @param forumComment
     */
    @Override
    public void addForumComment(ForumComment forumComment) {
        //添加用户评论
        forumCommentMapper.insert(forumComment);
        //帖子评论数加1
        Integer forumId = forumComment.getForumId();
    }
}
