package com.sg.ssm.service;

import com.sg.ssm.pojo.User;

public interface IUserService {
    /**
     * 注册
     * @param user
     * @return
     */
    public boolean register(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    public User login(User user);
}
