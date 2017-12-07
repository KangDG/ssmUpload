package com.sg.ssm.service.impl;

import com.sg.ssm.service.IUserService;
import org.springframework.stereotype.Service;

import com.sg.ssm.dao.IUserDao;
import com.sg.ssm.pojo.User;

@Service
public class UserServiceImpl implements IUserService {

	private IUserDao userDaoImpl;
	public IUserDao getUserDaoImpl() {
		return userDaoImpl;
	}
	public void setUserDaoImpl(IUserDao userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}


	@Override
	public boolean register(User user) {
		if(user!=null&&user.getUsername()!=null&&user.getPassword()!=null){
			return userDaoImpl.addUser(user);
		}
		return false;
	}
	@Override
	public User login(User user) {
		if(user!=null&&user.getUsername()!=null&&user.getPassword()!=null){
			return userDaoImpl.findUser(user);
		}
		return null;
	}

	
	
}
