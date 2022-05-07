package com.metro.exceptions;

public class LowBalanceException extends Exception {
	private static final long serialVersionUID = 1L;

	public LowBalanceException(String message){
		super(message);
	}
}
