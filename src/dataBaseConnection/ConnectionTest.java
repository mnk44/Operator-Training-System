package dataBaseConnection;

import java.io.IOException;
import java.sql.SQLException;

public class ConnectionTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException{
		try {
			try {
				ConnectionClass conect = new ConnectionClass("localhost", "5432", "SECPROIT", "postgres", "postgres");
				conect.crateConnection();
				System.out.println("Conexión establecida..."); 	
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
