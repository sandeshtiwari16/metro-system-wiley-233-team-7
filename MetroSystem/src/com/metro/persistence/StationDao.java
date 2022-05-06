package com.metro.persistence;

import java.util.List;

import com.metro.entity.Station;

public interface StationDao {
	public int getStationId(String stationName);
	public List<Station> getStationsList();
	public List<String> getStationNamesList();
}
