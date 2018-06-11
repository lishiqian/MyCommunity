package com.lsq.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsq.community.common.PageData;
import com.lsq.community.custom.ForumUserCustom;
import com.lsq.community.mapper.ForumMapper;
import com.lsq.community.mapper.ForumVisitorLogsMapper;
import com.lsq.community.mapper.UserMapper;
import com.lsq.community.po.Forum;
import com.lsq.community.po.ForumExample;
import com.lsq.community.po.ForumVisitorLogs;
import com.lsq.community.service.ForumService;
import com.lsq.community.util.JsonUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("forumService")
public class ForumServiceImpl implements ForumService{

    Logger logger = LogManager.getLogger(ForumServiceImpl.class);

    @Autowired
    private ForumMapper forumMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ForumVisitorLogsMapper forumVisitorLogsMapper;

    @Override
    public void addForum(Forum forum) {
        forumMapper.insert(forum);
    }

    /**
     * 根据用户id查询用户所有帖子
     * @param id
     * @return
     */
    @Override
    public List<Forum> selectForumsByUserId(Integer id) {
        ForumExample forumExample = new ForumExample();
        ForumExample.Criteria criteria = forumExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        return forumMapper.selectByExample(forumExample);
    }

    @Override
    public List<Forum> selectForumsByUserIdAndStatus(Integer id,Integer status) {
        ForumExample forumExample = new ForumExample();
        ForumExample.Criteria criteria = forumExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        criteria.andStatusEqualTo(status);
        //按照最后修改时间降序排序
        forumExample.setOrderByClause("last_update_time DESC");
        return forumMapper.selectByExample(forumExample);
    }




    /**
     * 根据id获取标签内容
     * @param id
     * @return
     */
    @Override
    public Forum selectForumsById(Integer id) {
        return forumMapper.selectByPrimaryKey(id);
    }

    //帖子阅读数量加1
    @Override
    public void addReaderNum(Integer forumId) {
        Forum forum = forumMapper.selectByPrimaryKey(forumId);
        forum.setReadingNum(forum.getReadingNum() + 1);
        forumMapper.updateByPrimaryKey(forum);
        logger.info("帖子id为:" + forumId + ",帖子浏览数+1，浏览数为：" + forum.getReadingNum());
    }

    @Override
    public void addReaderNum(Integer forumId, Integer userId) {
        //添加用户浏览记录
        ForumVisitorLogs forumVisitorLogs = new ForumVisitorLogs();
        forumVisitorLogs.setForumId(forumId);
        forumVisitorLogs.setUserId(userId);
        forumVisitorLogs.setCreateTime(new Date());

        addReaderNum(forumId);

        forumVisitorLogsMapper.insertSelective(forumVisitorLogs);
    }

    @Override
    public void addCommentNum(Integer forumId) {
        Forum forum = forumMapper.selectByPrimaryKey(forumId);
        forum.setComments(forum.getComments()+1);
        forumMapper.updateByPrimaryKey(forum);
        logger.info("帖子id为:" + forumId + ",帖子评论数+1，评论数为：" + forum.getComments());
    }

    @Override
    public void deleteForum(Integer forumId) {
        updateStatus(forumId,3);
    }

    @Override
    public void updateForum(Forum forum) {
        forumMapper.updateByPrimaryKeySelective(forum);
    }

    /**
     * //主页论坛帖子列表查询
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    @Override
    public PageData search(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum,pageSize);
        ForumExample forumExample = new ForumExample();
        forumExample.setOrderByClause("reading_num DESC");
        ForumExample.Criteria criteria = forumExample.createCriteria();
        criteria.andStatusEqualTo(1);
        List<Forum> forums = forumMapper.selectByExample(forumExample);
        PageInfo pageInfo = new PageInfo(forums);


        List<ForumUserCustom> forumUserCustoms = new ArrayList<ForumUserCustom>(forums.size());
        for (Forum forum : forums) {
            ForumUserCustom forumUserCustom = new ForumUserCustom();
            forumUserCustom.setForum(forum);
            //根据用户帖子创建id查询用户信息
            forumUserCustom.setUser(userMapper.selectByPrimaryKey(forum.getUserId()));

            forumUserCustoms.add(forumUserCustom);
        }
        PageData pageData = new PageData(pageInfo,forumUserCustoms);

        return pageData;
    }


    private void updateStatus(Integer forumId,Integer status){
        Forum forum = new Forum();
        forum.setId(forumId);
        forum.setStatus(status);
        forum.setLastUpdateTime(new Date());

        forumMapper.updateByPrimaryKeySelective(forum);
    }
}
