package com.lsq.community.custom;

import com.lsq.community.po.Forum;
import com.lsq.community.po.User;

public class ForumUserCustom {
    private Forum forum;
    private User user;

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
