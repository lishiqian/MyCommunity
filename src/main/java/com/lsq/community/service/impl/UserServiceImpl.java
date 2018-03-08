package com.lsq.community.service.impl;

import com.lsq.community.mapper.UserMapper;
import com.lsq.community.pojo.User;
import com.lsq.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lishiqian
 * @Date:Created in 2018/3/8 18:06
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }
}
