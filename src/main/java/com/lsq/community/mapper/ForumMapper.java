package com.lsq.community.mapper;

import com.lsq.community.po.Forum;
import com.lsq.community.po.ForumExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForumMapper {
    int countByExample(ForumExample example);

    int deleteByExample(ForumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Forum record);

    int insertSelective(Forum record);

    List<Forum> selectByExampleWithBLOBs(ForumExample example);

    List<Forum> selectByExample(ForumExample example);

    Forum selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Forum record, @Param("example") ForumExample example);

    int updateByExampleWithBLOBs(@Param("record") Forum record, @Param("example") ForumExample example);

    int updateByExample(@Param("record") Forum record, @Param("example") ForumExample example);

    int updateByPrimaryKeySelective(Forum record);

    int updateByPrimaryKeyWithBLOBs(Forum record);

    int updateByPrimaryKey(Forum record);
}