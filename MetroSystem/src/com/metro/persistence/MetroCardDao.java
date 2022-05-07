package com.metro.persistence;

import com.metro.entity.MetroCard;

public interface MetroCardDao {
	public int registerMetroCard(MetroCard metroCard);
	public int ifMetroCardExists(int metroCardId);
}
