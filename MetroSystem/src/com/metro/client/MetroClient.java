package com.metro.client;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.metro.presentation.MetroPresentation;
import com.metro.presentation.MetroPresentationImpl;

public class MetroClient {
	
	public static void main(String[] args) {
		MetroPresentation metroPresentation = new MetroPresentationImpl();
		Scanner scanner = new Scanner(System.in);
		while(true) {
			metroPresentation.showCardMenu();
			System.out.println("Enter your choice : ");
			try {
				int choice = scanner.nextInt();
				metroPresentation.performCardChoice(choice);
			}catch(InputMismatchException e) {
				System.out.println("Invalid Input");
				scanner.next();
			}
		}
	}

}
