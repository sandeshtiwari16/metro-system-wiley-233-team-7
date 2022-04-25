package com.metro.persistence;

import com.metro.entity.Users;

public interface UsersDao {
	
	public int getUserId(Users users);
	public int registerNewUser(Users users);
}
