package com.lsq.community.mapper;

import com.lsq.community.pojo.User;

/**
 * @author lishiqian
 * @Date:Created in 2018/3/8 17:56
 */
public interface UserMapper {

    public User selectUserById(Long id);

}
