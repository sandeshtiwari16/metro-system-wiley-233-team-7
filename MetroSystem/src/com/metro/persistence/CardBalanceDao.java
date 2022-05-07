package com.metro.persistence;

public interface CardBalanceDao {
	public double getCardBalance(int metroCardId);
	public int addCardBalance(int metroCardId, double amount);
}
