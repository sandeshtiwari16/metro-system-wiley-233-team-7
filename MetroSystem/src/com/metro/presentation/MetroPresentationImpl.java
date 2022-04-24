package com.metro.presentation;

import java.util.Scanner;

import com.metro.entity.Users;
import com.metro.service.UsersService;
import com.metro.service.UsersServiceImpl;

public class MetroPresentationImpl implements MetroPresentation {

	UsersService usersService = new UsersServiceImpl();
	Scanner sc = new Scanner(System.in);
			
	@Override
	public void showMenu() {
		System.out.println("===============================");
		System.out.println("1. Register for new Card ");
		System.out.println("2. Swipe In ");
		System.out.println("3. Swipe Out ");
		System.out.println("4. Add Balance to Card");
		System.out.println("5. Check Balance of Card");
		System.out.println("6. Exit ");
		
	}

	@Override
	public void showCardMenu() {
		System.out.println("===============================");
		System.out.println("1. New User ");
		System.out.println("2. Existing User ");
		System.out.println("3. Exit ");
		System.out.println("Enter your choice : ");
	}
	
	@Override
	public void performChoice(int choice) {
		int metroCardId=0;
		switch(choice) {
		case 1: 
				showCardMenu();
				int cardChoice= sc.nextInt();
				performCardChoice(cardChoice);
				break;
		case 2:
				break;
		case 3:
			    
				break;
		case 4: 
			    
			    break;
		case 5:
			    System.out.println("Enter Metro card ID : ");
                metroCardId=sc.nextInt();
                double currentBalance = usersService.getCardBalance(metroCardId);
                if(currentBalance <=0)
                  System.out.println("Incorrect Metro Id");
                else
               	  System.out.println("Your current Balance is: "+currentBalance);
			    break;
		case 6: System.exit(0);
		default : System.out.println("Enter valid choice !");
		
		}
		
	}
	
	@Override
	public void performCardChoice(int cardChoice) {
		switch(cardChoice) {
		case 1: System.out.println("Enter your name : ");
				String userName = sc.next();
				System.out.println("Enter your phone number : ");
				String phoneNumber = sc.next();
				Users users = new Users(userName, phoneNumber);
				if(usersService.registerNewUser(users)) {
					System.out.println("User Registered Succesfully...");
					System.out.println("Your User Id is : " + usersService.getUserId(users));
					System.out.println("Please keep it safe for future use...");
				}
				else
					System.out.println("User Registration failed!");
				break;
		case 2:	System.out.println("Enter your user id : ");
			   	int userId = sc.nextInt();
				if(usersService.registerMetroID(userId)) {
					System.out.println("Metro Id Registered Succesfully...");
					System.out.println("Your Metro Id is : " + usersService.getMetroId(userId));
					System.out.println("Please keep it safe for future use...");
				}
				else
					System.out.println("Metro ID Registration failed!");
				break;
		case 3:
				break;
		default : System.out.println("Enter valid choice !");
		}
	
	}
	
}
