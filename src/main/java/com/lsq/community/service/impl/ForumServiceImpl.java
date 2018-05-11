package com.lsq.community.service.impl;

import com.lsq.community.mapper.ForumMapper;
import com.lsq.community.pojo.Forum;
import com.lsq.community.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("forumService")
public class ForumServiceImpl implements ForumService{

    @Autowired
    private ForumMapper forumMapper;

    @Override
    public void addForum(Forum forum) {
        forumMapper.addForum(forum);
    }
}
