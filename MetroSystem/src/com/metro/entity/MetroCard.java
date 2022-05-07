package com.metro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class MetroCard {

	private String userName;
	private String phoneNumber;
	private double balance;
	
	public MetroCard(String username, String phoneNumber) {
		this.userName = username;
		this.phoneNumber = phoneNumber;
		this.balance = 100.0;
	}
}
