package com.lsq.community.service.impl;

import com.lsq.community.common.ErrorCode;
import com.lsq.community.mapper.UserMapper;
import com.lsq.community.po.User;
import com.lsq.community.po.UserExample;
import com.lsq.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lishiqian
 * @Date:Created in 2018/3/8 18:06
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;



    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectUsername(String email) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(email);
        List<User> users = userMapper.selectByExample(userExample);
        return users.isEmpty() ? null:users.get(0);
    }

    /**
     * 判断emile是否存在数据库
     * @param email
     * @return
     */
    @Override
    public boolean selectEmailExsit(String email) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(email);
        List<User> users = userMapper.selectByExample(userExample);
        return !users.isEmpty();
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }


}
