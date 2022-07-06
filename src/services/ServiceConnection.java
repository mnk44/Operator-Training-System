package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

import connectionDB.ConnectionClass;
import connectionDB.ReadConnectionFile;

/**
 * Permite establecer la conexion directa con la base de datos 
 * para el trabajo con la misma
 * 
 * @author Mnk
 *
 */

public class ServiceConnection {
	static LinkedList<String> properties = new LinkedList<String>();
	
	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException{
		properties = ReadConnectionFile.connectionProperties();
		//ConnectionClass connectionClass = new ConnectionClass(properties.get(0), properties.get(1), properties.get(2), properties.get(3), properties.get(4));
		ConnectionClass connectionClass = new ConnectionClass("localhost", "5432", "Sistema SECPROIT", "postgres", "123");
		Connection connection = connectionClass.crateConnection();
		return connection;
	}
}
