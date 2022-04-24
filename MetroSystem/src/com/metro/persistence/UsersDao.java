package com.metro.persistence;

import java.util.List;

import com.metro.entity.Users;

public interface UsersDao {
	
	public int getUserId(Users users);
	public int registerNewUser(Users users);
	public List<Integer> getMetroCardId(int userId);
	public int registerMetroCardId(int uderId);
	public double getCardBalance(int metroCardId);
}
