package com.lsq.community.mapper;

import com.lsq.community.po.ForumVisitorLogs;
import com.lsq.community.po.ForumVisitorLogsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ForumVisitorLogsMapper {
    int countByExample(ForumVisitorLogsExample example);

    int deleteByExample(ForumVisitorLogsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ForumVisitorLogs record);

    int insertSelective(ForumVisitorLogs record);

    List<ForumVisitorLogs> selectByExample(ForumVisitorLogsExample example);

    ForumVisitorLogs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ForumVisitorLogs record, @Param("example") ForumVisitorLogsExample example);

    int updateByExample(@Param("record") ForumVisitorLogs record, @Param("example") ForumVisitorLogsExample example);

    int updateByPrimaryKeySelective(ForumVisitorLogs record);

    int updateByPrimaryKey(ForumVisitorLogs record);
}