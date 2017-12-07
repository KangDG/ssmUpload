package com.sg.ssm.dao;

import com.sg.ssm.pojo.User;

public interface IUserDao {

    /**
     * 添加用户
     * @param user
     * @return
     */
    public boolean addUser(User user);

    /**
     * 查找用户
     * @param user
     * @return
     */
    public User findUser(User user);
}
