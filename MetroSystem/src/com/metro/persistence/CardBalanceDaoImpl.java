package com.metro.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardBalanceDaoImpl implements CardBalanceDao {

	@Override
	public double getCardBalance(int metroCardId) {
		double currentBalance=0;
		PreparedStatement preparedStatement = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", 
				"root", "wiley");) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("SELECT BALANCE FROM METRO_CARD WHERE METRO_CARD_ID=?");
			preparedStatement.setInt(1,metroCardId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				currentBalance = resultSet.getDouble("balance");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return currentBalance;
	}

}
