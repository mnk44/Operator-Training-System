package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dbConnection.ConnectionClass;

public class ConnectionService {

	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException{
		ConnectionClass connectionClass = new ConnectionClass("localhost", "5432", "SECPROIT", "postgres", "postgres");
		Connection connection = connectionClass.crateConnection();
		if(connection == null){
			JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return connection;
	}
}
