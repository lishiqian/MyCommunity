package com.lsq.community.service;

import com.lsq.community.common.ErrorCode;
import com.lsq.community.po.User;

/**
 * @author lishiqian
 * @Date:Created in 2018/3/8 18:05
 */
public interface UserService {
    User selectUserById(Integer id);

    User selectUsername(String email);

    boolean selectEmailExsit(String email);

    void addUser(User user);

    /**
     * 修改用户头像
     * @param userId
     * @param imgPath
     */
    void updateUserImg(Integer userId,String imgPath);

    void updateUser(User loginUser);
}
