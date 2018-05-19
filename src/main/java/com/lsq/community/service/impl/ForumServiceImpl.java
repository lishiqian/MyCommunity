package com.lsq.community.service.impl;

import com.lsq.community.mapper.ForumMapper;
import com.lsq.community.po.Forum;
import com.lsq.community.po.ForumExample;
import com.lsq.community.service.ForumService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("forumService")
public class ForumServiceImpl implements ForumService{

    Logger logger = LogManager.getLogger(ForumServiceImpl.class);

    @Autowired
    private ForumMapper forumMapper;

    @Override
    public void addForum(Forum forum) {
        forumMapper.insert(forum);
    }

    /**
     * 根据用户id查询用户所有帖子
     * @param id
     * @return
     */
    @Override
    public List<Forum> selectForumsByUserId(Integer id) {
        ForumExample forumExample = new ForumExample();
        ForumExample.Criteria criteria = forumExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        return forumMapper.selectByExample(forumExample);
    }

    /**
     * 根据id获取标签内容
     * @param id
     * @return
     */
    @Override
    public Forum selectForumsById(Integer id) {
        return forumMapper.selectByPrimaryKey(id);
    }

    //帖子阅读数量加1
    @Override
    public void addReaderNum(Integer forumId) {
        Forum forum = forumMapper.selectByPrimaryKey(forumId);
        forum.setReadingNum(forum.getReadingNum() + 1);
        forumMapper.updateByPrimaryKey(forum);
        logger.info("帖子id为:" + forumId + ",帖子浏览数+1，浏览数为：" + forum.getReadingNum());
    }

    @Override
    public void addReaderNum(Integer forumId, Integer userId) {
        addReaderNum(forumId);
    }
}
