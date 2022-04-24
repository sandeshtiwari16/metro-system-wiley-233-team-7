package com.metro.presentation;
import java.util.Scanner;
public class MetroPresentationImpl implements MetroPresentation {

	@Override
	public void showMenu() {
		
		System.out.println("1. Register for new Card ");
		System.out.println("2. Swipe In ");
		System.out.println("3. Swipe Out ");
		System.out.println("4. Add Balance to Card");
		System.out.println("5. Check Balance of Card");
		System.out.println("6. Exit ");
		
	}

	@Override
	public void showCardMenu() {
		System.out.println("1. New User ");
		System.out.println("2. Existing User ");
		System.out.println("3. Exit ");
		System.out.println("Enter your choice : ");
	}
	
	@Override
	public void performChoice(int choice) {
		Scanner sc = new Scanner(System.in);
		switch(choice) {
		case 1: 
				showCardMenu();
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
		default : System.out.println("Enter valid choice !");
		
		}
		
	}
	
	@Override
	public void performCardChoice(int cardChoice) {
		switch(cardChoice) {
		case 1: 
				break;
		case 2:
				break;
		case 3:
				break;
		default : System.out.println("Enter valid choice !");
		}
	
	}
	
}
