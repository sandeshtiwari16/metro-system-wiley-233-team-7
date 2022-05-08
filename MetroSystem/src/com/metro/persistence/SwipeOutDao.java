package com.metro.persistence;

public interface SwipeOutDao {
	public double swipeOut(int metroCardId, int destinationStationId);
	public boolean checkIfSwipedIn(int metroCardId);
	public double CalculateFare(int metroCardId, int destinationStationId);
}
