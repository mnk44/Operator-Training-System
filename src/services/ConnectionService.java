package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dbConnection.ConnectionClass;

public class ConnectionService {

	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException{
		ConnectionClass connectionClass = new ConnectionClass("localhost", "5433", "SECPROIT", "postgres", "postgres");
		Connection connection = connectionClass.crateConnection();
		if(connection == null){
			return null;
		}
		return connection;
	}
}
