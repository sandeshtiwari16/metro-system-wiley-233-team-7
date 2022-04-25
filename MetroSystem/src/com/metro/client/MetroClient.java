package com.metro.client;

import java.util.Scanner;

import com.metro.presentation.MetroPresentation;
import com.metro.presentation.MetroPresentationImpl;

public class MetroClient {
	
	public static void main(String[] args) {
		MetroPresentation metroPresentation = new MetroPresentationImpl();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while(true) {
			metroPresentation.showMenu();
			System.out.println("Enter your choice : ");
			int choice = scanner.nextInt();
			metroPresentation.performChoice(choice);
		}
	}

}
