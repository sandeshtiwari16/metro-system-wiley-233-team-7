package com.metro.service;

import com.metro.entity.MetroCard;

public interface MetroCardService {
	public int registerMetroCard(MetroCard metroCard);
	public boolean ifMetroCardExists(int metroCardId);
	public boolean checkifSwipedIn(int metroCardId);
}
