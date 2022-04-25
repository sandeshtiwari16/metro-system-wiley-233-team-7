package com.metro.service;

import com.metro.persistence.CardBalanceDao;
import com.metro.persistence.CardBalanceDaoImpl;

public class CardBalanceServiceImpl implements CardBalanceService {

	CardBalanceDao cardBalanceDao = new CardBalanceDaoImpl();
	
	@Override
	public double getCardBalance(int metroCardId) {
		return cardBalanceDao.getCardBalance(metroCardId);
	}
}
