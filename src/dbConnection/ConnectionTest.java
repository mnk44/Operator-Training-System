package dbConnection;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException{
		connectionTest();
	}
	
	public static void connectionTest(){
		try {
			try {
				ConnectionClass conect = new ConnectionClass("localhost", "5431", "SECPROIT", "postgres", "postgres");
				conect.crateConnection();
				System.out.println("Conexión establecida...");
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error de conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
