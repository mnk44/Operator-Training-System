package systemServices;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import dataBaseConnection.ConnectionClass;

public class ConnectionService {

	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException{
		ConnectionClass connectionClass = new ConnectionClass("localhost", "5432", "SECPROIT", "postgres", "postgres");
		Connection connection = connectionClass.crateConnection();
		return connection;
	}
}
