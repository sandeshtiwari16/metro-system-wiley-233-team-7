package com.metro.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.metro.entity.Station;

public class StationDaoImpl implements StationDao {
	
	@Override
	public List<Station> getStationsList() {
		List<Station> stations = new ArrayList<Station>();

		java.sql.Statement statement = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system", 
				"root", "wiley");) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM STATION");

			while (resultSet.next()) {
				int stationId = resultSet.getInt("STATION_ID");
				String stationName = resultSet.getString("STATION_NAME");
				String stationCode = resultSet.getString("STATION_CODE");
				Station station = new Station(stationId, stationName, stationCode);
				stations.add(station);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stations;
	}
}
