package com.metro.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.metro.entity.MetroCard;
import com.metro.entity.Station;
import com.metro.exceptions.InvalidStationException;
import com.metro.service.*;


public class MetroPresentationImpl implements MetroPresentation {

	MetroCardService metroCardService = new MetroCardServiceImpl();
	CardBalanceService cardBalanceService = new CardBalanceServiceImpl();
	SwipeInService swipeInService = new SwipeInServiceImpl();
	StationService stationService = new StationServiceImpl();
	SwipeOutService swipeOutService = new SwipeOutServiceImpl();
	
	Scanner sc = new Scanner(System.in);
			
	private int metroCardId = 0;
	
	@Override
	public void showCardMenu() {
		System.out.println("===============================");
		System.out.println("1. Create New Metro Card ");
		System.out.println("2. Already Have A Metro Card ");
		System.out.println("3. Exit ");
	}
	
	@Override
	public void performCardChoice(int cardChoice) {
		switch(cardChoice) {
		case 1: System.out.println("Enter Your Name : ");
				String userName = sc.next();
				System.out.println("Enter Your Phone Number : ");
				String phoneNumber = sc.next();
				MetroCard metroCard = new MetroCard(userName, phoneNumber);
				metroCardId = metroCardService.registerMetroCard(metroCard); 
				if(metroCardId != 0) {
					System.out.println("Metro Card Generated Succesfully...");
					System.out.println("Your Metro Card Id is : " + metroCardId);
					System.out.println("Please Keep It Safe For Future Use...");
				}
				else
					System.out.println("User Registration Failed!");
				break;
		case 2:	System.out.println("Enter Your Metro Card Id : ");
			   	metroCardId = sc.nextInt();
				if(metroCardService.ifMetroCardExists(metroCardId)) {
					if(metroCardService.checkifSwipedIn(metroCardId)) {
						showMenuSwipeOut();
						int choice = sc.nextInt();
						performChoiceSwipeOut(choice);
					}else {
						showMenuSwipeIn();
						int choice = sc.nextInt();
						performChoiceSwipeIn(choice);
					}
					
				}
				else
					System.out.println("Metro Card ID Does Not Exist");
				break;
		case 3:	System.exit(0);
				break;
		default : System.out.println("Invalid choice!");
		}
	}
	
	@Override
	public void showMenuSwipeOut() {
		System.out.println("===============================");
		System.out.println("1. Swipe Out ");
		System.out.println("2. Add Balance to Metro Card");
		System.out.println("3. Check Balance of Metro Card");
		System.out.println("4. Exit ");
	}	
	
	@Override
	public void performChoiceSwipeOut(int choice) {
		List<Station> stations =  stationService.getStationsList();
		List<Integer> stationIds = new ArrayList<>();
		List<String> stationCodes = new ArrayList<>();
		List<String> stationNames = new ArrayList<>();
		for(Station station : stations) {
			stationNames.add(station.getStationName());
			stationCodes.add(station.getStationCode());
			stationIds.add(station.getStationId());
		}
		int stationId = 0;
		switch(choice) {
		case 1:
				System.out.println("*************");
			    for(Station station : stations) {
			    	System.out.println(station.getStationName() + " -> " + station.getStationCode());
			    }
			    System.out.println("*************");
				System.out.println("Enter Destination Station Code : ");
				String destinationCode = sc.next();
				destinationCode = destinationCode.toUpperCase();
				try {
					if(!stationCodes.contains(destinationCode)) 
						throw new InvalidStationException("Incorrect Station Code!");
				}
				catch(InvalidStationException e) {
					System.out.println(e.getMessage());
					break;
				}
				stationId = stationIds.get(stationCodes.indexOf(destinationCode));
				double fare = swipeOutService.calculateFare(metroCardId, stationId);
				double balance = swipeOutService.checkSwipeOut(metroCardId, stationId);
				if(balance != -1) {
					System.out.println("You Have Successfully Swiped Out With Card Balance As : " + balance);
					System.out.println("Your Total Fare is : " + fare);
				}
				else System.out.println("Access Denied");
				break;
		case 2: 
			    System.out.println("Enter Amount To Add : ");
			    double amount = sc.nextDouble();
			    if(amount < 0.0) {
			    	System.out.println("Negative Amount Not Allowed!");
			    	break;
			    }
			    if(amount == 0.0) {
			    	System.out.println("Invalid Amount");
			    	break;
			    }
			    if(cardBalanceService.addCardBalance(metroCardId, amount))
			    	System.out.println("Amount Added Succesfully...");
			    else
			    	System.out.println("Amount Addition Failed!");
			    break;
		case 3:
                System.out.println(cardBalanceService.getCardBalance(metroCardId));
			    break;
		case 4: 
				break;
		default : System.out.println("Invalid choice!");
		
		}
	}
	
	@Override
	public void showMenuSwipeIn() {
		System.out.println("===============================");
		System.out.println("1. Swipe In ");
		System.out.println("2. Add Balance to Metro Card");
		System.out.println("3. Check Balance of Metro Card");
		System.out.println("4. Exit ");
	}
	
	@Override
	public void performChoiceSwipeIn(int choice) {
		List<Station> stations =  stationService.getStationsList();
		List<Integer> stationIds = new ArrayList<>();
		List<String> stationCodes = new ArrayList<>();
		List<String> stationNames = new ArrayList<>();
		for(Station station : stations) {
			stationNames.add(station.getStationName());
			stationCodes.add(station.getStationCode());
			stationIds.add(station.getStationId());
		}
		int stationId = 0;
		switch(choice) {
		case 1:
				System.out.println("*************");
				for(Station station : stations) {
					System.out.println(station.getStationName() + " -> " + station.getStationCode());
				}
				System.out.println("*************");
				System.out.println("Enter Source Station Code : ");
				String sourceCode = sc.next();
				sourceCode = sourceCode.toUpperCase();
				try {
					if(!stationCodes.contains(sourceCode)) 
						throw new InvalidStationException("Incorrect Station Code!");
				}
				catch(InvalidStationException e) {
					System.out.println(e.getMessage());
					break;
				}
				stationId = stationIds.get(stationCodes.indexOf(sourceCode));
				if(swipeInService.checkSwipeIn(metroCardId, stationId))
					System.out.println("You have successfully swiped in at station : "
							+ stationNames.get(stationCodes.indexOf(sourceCode)));
				else System.out.println("Access Denied");
				break;
		case 2: 
			    System.out.println("Enter Amount To Add : ");
			    double amount = sc.nextDouble();
			    if(amount < 0.0) {
			    	System.out.println("Negative Amount Not Allowed!");
			    	break;
			    }
			    if(amount == 0.0) {
			    	System.out.println("Invalid Amount");
			    	break;
			    }
			    if(cardBalanceService.addCardBalance(metroCardId, amount))
			    	System.out.println("Amount Added Succesfully...");
			    else
			    	System.out.println("Amount Addition Failed!");
			    break;
		case 3:
                System.out.println(cardBalanceService.getCardBalance(metroCardId));
			    break;
		case 4: 
				break;
		default : System.out.println("Invalid choice!");
		
		}
	}
}
