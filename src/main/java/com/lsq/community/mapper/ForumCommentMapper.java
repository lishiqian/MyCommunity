package com.lsq.community.mapper;

import com.lsq.community.po.ForumComment;
import com.lsq.community.po.ForumCommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForumCommentMapper {
    int countByExample(ForumCommentExample example);

    int deleteByExample(ForumCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ForumComment record);

    int insertSelective(ForumComment record);

    List<ForumComment> selectByExample(ForumCommentExample example);

    ForumComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ForumComment record, @Param("example") ForumCommentExample example);

    int updateByExample(@Param("record") ForumComment record, @Param("example") ForumCommentExample example);

    int updateByPrimaryKeySelective(ForumComment record);

    int updateByPrimaryKey(ForumComment record);
}