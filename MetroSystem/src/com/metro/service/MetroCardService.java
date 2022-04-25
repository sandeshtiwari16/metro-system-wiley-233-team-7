package com.metro.service;

import java.util.List;

public interface MetroCardService {
	public List<Integer> getMetroCardId(int userId);
	public boolean registerMetroCardID(int userId);
}
