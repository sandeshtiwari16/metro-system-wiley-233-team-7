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
	public void registerMetroId() {
		// TODO Auto-generated method stub

	}

}
