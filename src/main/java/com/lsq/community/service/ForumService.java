package com.lsq.community.service;


import com.lsq.community.custom.ForumUserCustom;
import com.lsq.community.po.Forum;

import java.util.List;

public interface ForumService {
    void addForum(Forum forum);

    List<Forum> selectForumsByUserId(Integer id);

    List<ForumUserCustom> selectForumsOrderByReadingNum();

    Forum selectForumsById(Integer id);

    void addReaderNum(Integer forumId);

    void addReaderNum(Integer forumId,Integer userId);

    void addCommentNum(Integer forumId);
}
