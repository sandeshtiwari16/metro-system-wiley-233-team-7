package com.metro.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardBalanceDaoImpl implements CardBalanceDao {

	@Override
	public double getCardBalance(int metroCardId) {
		double currentBalance=0.0;
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

	@Override
	public int addCardBalance(int metroCardId, double amount) {
		int rows = 0;
		PreparedStatement preparedStatement = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", 
				"root", "wiley");) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			double balance = getCardBalance(metroCardId);
			preparedStatement = connection.prepareStatement("UPDATE METRO_CARD SET BALANCE = ? WHERE METRO_CARD_ID = ?");
			preparedStatement.setDouble(1,(balance+amount));
			preparedStatement.setInt(2, metroCardId);
			
			rows = preparedStatement.executeUpdate();

		} catch (ClassNotFoundException e) {
			rows = 0;
		} catch (SQLException e) {
			rows = 0;
		}
		return rows;
	}

}
