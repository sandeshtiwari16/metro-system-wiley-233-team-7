package com.metro.service;

public interface SwipeOutService {
	public double checkSwipeOut(int metroCardId, int destinationStationId);
	public double calculateFare(int metroCardId, int destinationStationId);
}
