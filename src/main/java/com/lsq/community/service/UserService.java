package com.lsq.community.service;

import com.lsq.community.common.ErrorCode;
import com.lsq.community.po.User;

/**
 * @author lishiqian
 * @Date:Created in 2018/3/8 18:05
 */
public interface UserService {
    User selectUserById(Integer id);

    User selectUsername(String username);

    boolean selectEmailExsit(String email);

    void addUser(User user);
}
