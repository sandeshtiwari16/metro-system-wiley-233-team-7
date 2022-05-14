package com.metro.service;

import com.metro.persistence.SwipeOutDao;
import com.metro.persistence.SwipeOutDaoImpl;

public class SwipeOutServiceImpl implements SwipeOutService {

	SwipeOutDao swipeOutDao = new SwipeOutDaoImpl();
	
	@Override
	public double checkSwipeOut(int metroCardId, int destinationStationId) {
		return swipeOutDao.swipeOut(metroCardId, destinationStationId);
	}

	@Override
	public double calculateFare(int metroCardId, int destinationStationId) {
		return swipeOutDao.CalculateFare(metroCardId, destinationStationId);
	}

}
