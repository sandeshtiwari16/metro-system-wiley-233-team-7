package com.metro.service;

import com.metro.entity.MetroCard;
import com.metro.persistence.MetroCardDao;
import com.metro.persistence.MetroCardDaoImpl;

public class MetroCardServiceImpl implements MetroCardService {

	MetroCardDao metroCardDao = new MetroCardDaoImpl();

	@Override
	public int registerMetroCard(MetroCard metroCard) {
		return metroCardDao.registerMetroCard(metroCard);
	}

	@Override
	public boolean ifMetroCardExists(int metroCardId) {
		if(metroCardDao.ifMetroCardExists(metroCardId) > 0)
			return true;
		return false;
	}

	@Override
	public boolean checkifSwipedIn(int metroCardId) {
		return metroCardDao.checkIfSwipedIn(metroCardId);
	}
}
