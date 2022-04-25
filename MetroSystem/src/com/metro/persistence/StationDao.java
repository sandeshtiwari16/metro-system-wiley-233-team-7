package com.metro.persistence;

import java.util.List;

public interface StationDao {
	public int getStationId(String stationName);
	public List<String> getStationsList();
}
