package com.metro.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.metro.entity.MetroCard;

public class MetroCardDaoImpl implements MetroCardDao {
	
	@Override
	public int registerMetroCard(MetroCard metroCard) {
		int rows = 0;
		int id = 0;
		java.sql.Statement statement = null;
		PreparedStatement preparedStatement = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", 
				"root", "wiley");) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("INSERT INTO METRO_CARD(USERNAME, PHONE, BALANCE) VALUES (?,?,?)");
			preparedStatement.setString(1, metroCard.getUserName());
			preparedStatement.setString(2, metroCard.getPhoneNumber());
			preparedStatement.setDouble(3, metroCard.getBalance());
	
			rows = preparedStatement.executeUpdate();
			
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT MAX(METRO_CARD_ID) AS MAX FROM METRO_CARD");

			if (resultSet.next()) {
				 id = resultSet.getInt("MAX");
			}
			
		} catch (ClassNotFoundException e) {
			rows = 0;
		} catch (SQLException e) {
			rows = 0;
		}
		if(rows == 0)
			return rows;
		return id;
	}

	@Override
	public int ifMetroCardExists(int metroCardId) {
		int rows = 0;
		java.sql.Statement statement = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", 
				"root", "wiley");) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT METRO_CARD_ID FROM METRO_CARD WHERE METRO_CARD_ID = "+metroCardId);

			if (resultSet.next()) {
				 rows = resultSet.getInt("METRO_CARD_ID");
			}
			
		} catch (ClassNotFoundException e) {
			rows = 0;
		} catch (SQLException e) {
			rows = 0;
		}
		return rows;
	}
}
