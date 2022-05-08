package com.metro.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class SwipeOutDaoImpl implements SwipeOutDao {

	private CardBalanceDao cardBalance = new CardBalanceDaoImpl();
	
	@Override
	public double swipeOut(int metroCardId, int destinationStationId) {
		PreparedStatement preparedStatement = null;
		Timestamp current = Timestamp.from(Instant.now());
		double balance = cardBalance.getCardBalance(metroCardId);
		if(checkIfSwipedIn(metroCardId)) {
			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", 
					"root", "wiley");) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				double totalFare = CalculateFare(metroCardId, destinationStationId);
				preparedStatement = connection.prepareStatement("UPDATE JOURNEY "
						+ "SET DESTINATION_STATION_ID = ?, DESTINATION_TIME_STAMP = ?, TOTAL_FARE = ? "
						+ "WHERE METRO_CARD_ID = ? AND DESTINATION_STATION_ID IS NULL");
				preparedStatement.setInt(1, destinationStationId);
				preparedStatement.setTimestamp(2, current);
				preparedStatement.setDouble(3, totalFare);
				preparedStatement.setInt(4, metroCardId);
				
				preparedStatement.executeUpdate();
				
				preparedStatement = connection.prepareStatement("UPDATE METRO_CARD SET BALANCE = ? WHERE METRO_CARD_ID = ?");
				preparedStatement.setDouble(1, balance - totalFare);
				preparedStatement.setInt(2, metroCardId);
				
				preparedStatement.executeUpdate();
				
				return balance-totalFare;

			} catch (SQLException e) {
				System.out.println(e);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}else {
			System.out.println("Card Not In Use! Please Swipe In...");
		}
		return -1;
	}
	
	@Override
	public boolean checkIfSwipedIn(int metroCardId) {
		java.sql.Statement statement = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", 
				"root", "wiley");) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM JOURNEY WHERE METRO_CARD_ID = "+ metroCardId +" AND DESTINATION_STATION_ID IS NULL");
			
			if(resultSet.next()) {
				return true;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public double CalculateFare(int metroCardId, int destinationStationId) {
		java.sql.Statement statement = null;
		double fare = 0.0d;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", 
				"root", "wiley");) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM JOURNEY WHERE METRO_CARD_ID = "+ metroCardId +" AND DESTINATION_STATION_ID IS NULL");
			
			if(resultSet.next()) {
				int sourceStationId = resultSet.getInt("SOURCE_STATION_ID");
				fare = Math.abs(destinationStationId - sourceStationId) * 5;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fare;
	}

}
