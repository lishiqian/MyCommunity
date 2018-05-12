package com.lsq.community.service.impl;

import com.lsq.community.mapper.ForumMapper;
import com.lsq.community.po.Forum;
import com.lsq.community.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("forumService")
public class ForumServiceImpl implements ForumService{

    @Autowired
    private ForumMapper forumMapper;

    @Override
    public void addForum(Forum forum) {
        forumMapper.insert(forum);
    }
}
