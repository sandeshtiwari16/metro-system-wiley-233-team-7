package com.metro.service;

import com.metro.persistence.SwipeInDao;
import com.metro.persistence.SwipeInDaoImpl;

public class SwipeInServiceImpl implements SwipeInService {
	
	SwipeInDao swipeInDao = new SwipeInDaoImpl();

	@Override
	public boolean checkSwipeIn(int metroCardId, int sourceStationId) {
		return swipeInDao.swipeIn(metroCardId, sourceStationId);
	}
}
