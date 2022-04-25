package com.metro.service;

import java.util.List;

import com.metro.persistence.MetroCardDao;
import com.metro.persistence.MetroCardDaoImpl;

public class MetroCardServiceImpl implements MetroCardService {

	MetroCardDao metroCardDao = new MetroCardDaoImpl();
	
	@Override
	public List<Integer> getMetroCardId(int userId) {
		return metroCardDao.getMetroCardId(userId);
	}

	@Override
	public boolean registerMetroCardID(int userId) {
		if(metroCardDao.registerMetroCardId(userId) > 0)
			return true;
		return false;
	}
}
