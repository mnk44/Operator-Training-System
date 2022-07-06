package connectionDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import logic.ChargeFileProcess;

/**
 * Esta clase permite probar la conexion con la base de datos
 * 
 * @author Mnk
 *
 */

public class ConnectionTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException{
		try {
			try {
				ConnectionClass conect = new ConnectionClass("localhost", "5432", "Sistema SECPROIT", "postgres", "postgres");
				Connection connection = conect.crateConnection();
				System.out.println("Conexión establecida..."); 	
				
				ChargeFileProcess.chargeAnmBD(8);
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
