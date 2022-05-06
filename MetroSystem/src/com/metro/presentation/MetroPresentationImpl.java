package com.metro.presentation;

import java.util.List;
import java.util.Scanner;


import com.metro.entity.Users;
import com.metro.exceptions.InvalidStationException;
import com.metro.service.*;


public class MetroPresentationImpl implements MetroPresentation {

	UsersService usersService = new UsersServiceImpl();
	MetroCardService metroCardService = new MetroCardServiceImpl();
	CardBalanceService cardBalanceService = new CardBalanceServiceImpl();
	SwipeInService swipeInService = new SwipeInServiceImpl();
	StationService stationService = new StationServiceImpl();
	
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
				System.out.println("Enter Metro Card ID : ");
				metroCardId = sc.nextInt();
				System.out.println("*************");
				List<String> stationNames =  stationService.getStationNamesList();
				for(String stationName : stationNames)
					System.out.println(stationName);
				System.out.println("*************");
				System.out.println("Enter Source Station Name : ");
				String sourceStation = sc.next();
				try {
				if(!stationNames.contains(sourceStation.toLowerCase())) throw new InvalidStationException("Station does not exist");
				}
				catch(InvalidStationException e) {
					System.out.println(e.getMessage());
					break;
				}
				int stationId = stationService.getStationId(sourceStation);
				if(swipeInService.checkSwipeIn(metroCardId, stationId)) {
					System.out.println("You have successfully swiped in at the station "+ sourceStation);
				}
				else {
					System.out.println("Access Denied");
				}

				break;
		case 3:
			    
				break;
		case 4: 
			    
			    break;
		case 5:
			    System.out.println("Enter Metro Card ID : ");
                metroCardId = sc.nextInt();
                double currentBalance = cardBalanceService.getCardBalance(metroCardId);
                if(currentBalance == 0)
                  System.out.println("Incorrect Metro Card Id");
                else
               	  System.out.println("Your Current Balance is: Rs. " + currentBalance);
			    break;
		case 6: System.exit(0);
		default : System.out.println("Invalid choice!");
		
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
				if(metroCardService.registerMetroCardID(userId)) {
					System.out.println("Metro Card Id Generated Succesfully...");
					System.out.println("Your Metro Card Id is : " + metroCardService.getMetroCardId(userId));
					System.out.println("Please keep it safe for future use...");
				}
				else
					System.out.println("Metro Card ID Registration Failed!");
				break;
		case 3:
				break;
		default : System.out.println("Invalid choice!");
		}
	
	}
	
}
