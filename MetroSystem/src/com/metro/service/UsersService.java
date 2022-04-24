package com.metro.service;

import java.util.List;

import com.metro.entity.Users;

public interface UsersService {
	public int getUserId(Users Users);
	public boolean registerNewUser(Users users);
	public List<Integer> getMetroCardId(int userId);
	public boolean registerMetroCardID(int userId);
	public double getCardBalance(int metroCardId);
}