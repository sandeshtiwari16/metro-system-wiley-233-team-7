package com.metro.service;

import com.metro.entity.Users;
import com.metro.persistence.UsersDao;
import com.metro.persistence.UsersDaoImpl;

public class UsersServiceImpl implements UsersService {

	UsersDao usersDao = new UsersDaoImpl(); 
	
	@Override
	public boolean registerNewUser(Users users) {
		if(usersDao.registerNewUser(users) > 0)
			return true;
		return false;
	}

	@Override
	public int getUserId(Users users) {
		return usersDao.getUserId(users);
	}

	@Override
	public boolean registerMetroID(int userId) {
		if(usersDao.registerMetroId(userId)>0)
			return true;
		return false;
	}

	@Override
	public int getMetroId(int userId) {
		return usersDao.getMetroId(userId);
	}

}
