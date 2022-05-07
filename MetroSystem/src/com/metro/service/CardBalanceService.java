package com.metro.service;

public interface CardBalanceService {
	public double getCardBalance(int metroCardId);
	public boolean addCardBalance(int metroCardId, double amount);
}
