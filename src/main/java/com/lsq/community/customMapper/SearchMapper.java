package com.lsq.community.customMapper;

import com.lsq.community.po.Forum;

import java.util.List;

public interface SearchMapper {
    List<Forum> selectForumByKeyword(String word);
}
