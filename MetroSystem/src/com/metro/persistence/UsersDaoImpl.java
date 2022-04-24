package com.metro.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metro.entity.Users;

public class UsersDaoImpl implements UsersDao {
	
	@Override
	public int getUserId(Users users) {
		int userId = 0;
		PreparedStatement preparedStatement = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", 
				"root", "wiley");) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("SELECT USER_ID FROM USERS WHERE USER_NAME=?");
			preparedStatement.setString(1, users.getUserName());

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				userId = resultSet.getInt("user_id");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}
	
	@Override
	public int registerNewUser(Users users) {
		int rows = 0;
		PreparedStatement preparedStatement = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", 
				"root", "wiley");) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("INSERT INTO USERS(USER_NAME, PHONE_NUMBER) VALUES (?,?)");
			preparedStatement.setString(1, users.getUserName());
			preparedStatement.setString(2, users.getPhoneNumber());
	
			rows = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			rows = 0;
		} catch (SQLException e) {
			rows = 0;
		}
		return rows;
	}
	
	@Override
	public List<Integer> getMetroCardId(int userId) {
		List<Integer> list = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", 
				"root", "wiley");) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("SELECT METRO_CARD_ID FROM METRO_CARD WHERE USER_ID=?");
			preparedStatement.setInt(1,userId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getInt("metro_card_id"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int registerMetroCardId(int userId) {
		int rows = 0;
		PreparedStatement preparedStatement = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", 
				"root", "wiley");) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("INSERT INTO METRO_CARD(USER_ID, BALANCE) VALUES (?,?)");
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, 100);
	
			rows = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			rows = 0;
		} catch (SQLException e) {
			rows = 0;
		}
		return rows;
	}

	@Override
	public double getCardBalance(int metroCardId) {
		double currentBalance = 0;
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
