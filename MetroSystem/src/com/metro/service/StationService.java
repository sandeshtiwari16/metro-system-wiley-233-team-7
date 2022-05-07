package com.metro.service;

import java.util.List;

import com.metro.entity.Station;

public interface StationService {
	public int getStationId(String stationName);
	public List<Station> getStationsList();
}
