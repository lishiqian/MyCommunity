package com.lsq.community.service.impl;

import com.lsq.community.custom.ForumCommentCustom;
import com.lsq.community.mapper.ForumCommentMapper;
import com.lsq.community.mapper.UserMapper;
import com.lsq.community.po.ForumComment;
import com.lsq.community.po.ForumCommentExample;
import com.lsq.community.po.User;
import com.lsq.community.service.ForumCommentService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("forumCommentService")
public class ForumCommentServiceImpl implements ForumCommentService {

    Logger logger = LogManager.getLogger(ForumCommentServiceImpl.class);

    @Autowired
    private ForumCommentMapper forumCommentMapper;

    @Autowired
    private UserMapper userMapper;

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

    /**
     * 获取帖子评论内容（包括评论用户信息）
     * @param forumId
     * @return
     */
    @Override
    public List<ForumCommentCustom> selectForumCommentsByForunId(Integer forumId) {
        ForumCommentExample forumCommentExample = new ForumCommentExample();
        ForumCommentExample.Criteria criteria = forumCommentExample.createCriteria();
        criteria.andForumIdEqualTo(forumId);
        //查询状态为1 未删除
        criteria.andStatusEqualTo(1);
        List<ForumComment> forumComments = forumCommentMapper.selectByExample(forumCommentExample);

        //封住评论与用户信息
        List<ForumCommentCustom> forumCommentCustoms = new ArrayList<ForumCommentCustom>(forumComments.size());
        for (int i = 0; i < forumComments.size(); i++) {
            ForumCommentCustom forumCommentCustom = new ForumCommentCustom();
            forumCommentCustom.setForumComment(forumComments.get(i));
            forumCommentCustom.setUser(userMapper.selectByPrimaryKey(forumComments.get(i).getCreateUserId()));

            forumCommentCustoms.add(forumCommentCustom);
        }

        logger.info("读取用户评论信息完成----------");


        return forumCommentCustoms;
    }
}
