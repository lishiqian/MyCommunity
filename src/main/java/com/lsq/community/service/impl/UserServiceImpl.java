package com.lsq.community.service.impl;

import com.lsq.community.mapper.UserMapper;
import com.lsq.community.po.User;
import com.lsq.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
