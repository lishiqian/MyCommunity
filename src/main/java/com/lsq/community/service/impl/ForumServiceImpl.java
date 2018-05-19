package com.lsq.community.service.impl;

import com.lsq.community.mapper.ForumMapper;
import com.lsq.community.po.Forum;
import com.lsq.community.po.ForumExample;
import com.lsq.community.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("forumService")
public class ForumServiceImpl implements ForumService{

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
}
