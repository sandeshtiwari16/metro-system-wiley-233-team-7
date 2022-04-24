package com.metro.service;

import java.util.List;

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
	public  List<Integer> getMetroCardId(int userId) {
		return usersDao.getMetroCardId(userId);
	}
	
	@Override
	public int getUserId(Users users) {
		return usersDao.getUserId(users);
	}

	@Override
	public boolean registerMetroCardID(int userId) {
		if(usersDao.registerMetroCardId(userId)>0)
			return true;
		return false;
	}

	@Override
	public double getCardBalance(int metroCardId) {
		return usersDao.getCardBalance(metroCardId);
	}

}
