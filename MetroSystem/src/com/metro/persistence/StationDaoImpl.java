package com.metro.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDaoImpl implements StationDao {

	@Override
	public int getStationId(String stationName) {
		int stationId = 0;
		PreparedStatement preparedStatement = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", 
				"root", "wiley");) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("SELECT STATION_ID FROM STATION WHERE STATION_NAME=?");
			preparedStatement.setString(1, stationName);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				stationId = resultSet.getInt("STATION_ID");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stationId;
	}
	
	@Override
	public List<String> getStationsList() {
		List<String> stations = new ArrayList<>();

		java.sql.Statement statement = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", 
				"root", "wiley");) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT STATION_NAME FROM STATION");

			while (resultSet.next()) {
				stations.add(resultSet.getString("STATION_NAME"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stations;
	}

}
