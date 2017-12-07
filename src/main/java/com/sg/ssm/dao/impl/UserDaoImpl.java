package com.sg.ssm.dao.impl;

import com.sg.ssm.dao.IUserDao;
import com.sg.ssm.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends SqlSessionDaoSupport implements IUserDao {


    @Override
    public boolean addUser(User user) {
        int result = super.getSqlSession().insert("com.sg.ssm.pojo.userMapper.addUser", user);
        return result > 0 ;
    }

    @Override
    public User findUser(User user) {
        System.out.println("findUser.....");
        return super.getSqlSession().selectOne("com.sg.ssm.pojo.userMapper.findUser", user);
    }

}