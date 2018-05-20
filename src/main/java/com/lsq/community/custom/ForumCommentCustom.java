package com.lsq.community.custom;

import com.lsq.community.po.ForumComment;
import com.lsq.community.po.User;

public class ForumCommentCustom {
    private ForumComment forumComment;
    private User user;

    public ForumComment getForumComment() {
        return forumComment;
    }

    public void setForumComment(ForumComment forumComment) {
        this.forumComment = forumComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
