package com.metro.presentation;
import java.util.Scanner;
public class MetroPresentationImpl implements MetroPresentation {

	@Override
	public void showChoice() {
		
		System.out.println("1. Register for new Card ");
		System.out.println("2. Swipe In ");
		System.out.println("3. Swipe Out ");
		System.out.println("4. Add Balance ");
		System.out.println("5. Check Balance ");
		System.out.println("6. Exit ");
		
	}

	@Override
	public void showCardChoice() {
		System.out.println("1. New User ");
		System.out.println("2. Existing User ");
		System.out.println("3. Exit ");
	}
	
	@Override
	public void performChoice(int choice) {
		Scanner sc = new Scanner(System.in);
		switch(choice) {
		case 1: 
				showCardChoice();
				int choice2= sc.nextInt();
				performCardChoice(choice2);
				break;
		case 2:
				break;
		case 3:
				break;
		case 4:
				break;
		case 5:
				break;
		case 6: System.exit(0);
				break;
		default : System.out.println("Enter valid choice !");
		
		}
		
	}
	
	@Override
	public void performCardChoice(int choice2) {
		switch(choice2) {
		case 1: 
				break;
		case 2:
				break;
		case 3:
				showChoice();
				break;
		default : System.out.println("Enter valid choice !");
		}
	
	}
	
}
