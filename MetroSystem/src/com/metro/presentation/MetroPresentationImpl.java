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
					showMenu();
					int choice = sc.nextInt();
					performChoice(choice);
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
	public void showMenu() {
		System.out.println("===============================");
		System.out.println("1. Swipe In ");
		System.out.println("2. Swipe Out ");
		System.out.println("3. Add Balance to Metro Card");
		System.out.println("4. Check Balance of Metro Card");
		System.out.println("5. Exit ");
	}	
	
	@Override
	public void performChoice(int choice) {
		switch(choice) {
		case 1:
				System.out.println("*************");
				List<Station> stations =  stationService.getStationsList();
				List<Integer> stationIds = new ArrayList<>();
				List<String> stationCodes = new ArrayList<>();
				List<String> stationNames = new ArrayList<>();
				for(Station station : stations) {
					stationNames.add(station.getStationName());
					stationCodes.add(station.getStationCode());
					stationIds.add(station.getStationId());
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
				int stationId = stationIds.get(stationCodes.indexOf(sourceCode));
				if(swipeInService.checkSwipeIn(metroCardId, stationId))
					System.out.println("You have successfully swiped in at station : "
							+ stationNames.get(stationCodes.indexOf(sourceCode)));
				else System.out.println("Access Denied");
				break;
		case 2:
			    
				break;
		case 3: 
			    System.out.println("Enter Amount To Add : ");
			    double amount = sc.nextDouble();
			    if(cardBalanceService.addCardBalance(metroCardId, amount))
			    	System.out.println("Amount Added Succesfully...");
			    else
			    	System.out.println("Amount Addition Failed!");
			    break;
		case 4:
                System.out.println(cardBalanceService.getCardBalance(metroCardId));
			    break;
		case 5: 
				break;
		default : System.out.println("Invalid choice!");
		
		}
	}
	
	
	
}
