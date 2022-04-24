package com.metro.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public int registerMetroId(int userId) {
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
	public int getMetroId(int userId) {
		int metroId = 0;
		PreparedStatement preparedStatement = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", 
				"root", "wiley");) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("SELECT METRO_CARD_ID FROM METRO_CARD WHERE USER_ID=?");
			preparedStatement.setInt(1,userId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				metroId = resultSet.getInt("metro_card_id");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return metroId;
	}

}
