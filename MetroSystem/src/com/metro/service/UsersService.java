package com.metro.service;

import com.metro.entity.Users;

public interface UsersService {
	public int getUserId(Users Users);
	public boolean registerNewUser(Users users);
	public boolean registerMetroID(int userId);
	public int getMetroId(int userId);
	public double getCardBalance(int metroCardId);
}