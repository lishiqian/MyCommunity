package com.lsq.community.service;

import com.lsq.community.pojo.User;

/**
 * @author lishiqian
 * @Date:Created in 2018/3/8 18:05
 */
public interface UserService {
    public User selectUserById(Long id);
}
