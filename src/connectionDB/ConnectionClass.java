package connectionDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Es la clase que permite la conexion con la base de datos
 * 
 * @author Mnk
 *
 */

public class ConnectionClass {
	static String HOST;
	static String PORT;
	static String DATABASE;
	static String USER;
	static String PASS;
	
	public ConnectionClass(String HOST, String PORT, String DATABASE, String USER, String PASS) {
		super();
		ConnectionClass.HOST = HOST;
		ConnectionClass.PORT = PORT;
		ConnectionClass.DATABASE = DATABASE;
		ConnectionClass.USER = USER;
		ConnectionClass.PASS = PASS;
	}

	public Connection crateConnection() throws IOException, ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE;
		Connection connection = DriverManager.getConnection(url,USER,PASS);
		if(connection != null){
			return connection;
		}
		return null;
	}
}
