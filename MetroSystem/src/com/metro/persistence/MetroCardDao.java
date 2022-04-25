package com.metro.persistence;

import java.util.List;

public interface MetroCardDao {
	public List<Integer> getMetroCardId(int userId);
	public int registerMetroCardId(int uderId);
}
