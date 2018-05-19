package com.lsq.community.service;


import com.lsq.community.po.Forum;

import java.util.List;

public interface ForumService {
    void addForum(Forum forum);

    List<Forum> selectForumsByUserId(Integer id);

    Forum selectForumsById(Integer id);
}
