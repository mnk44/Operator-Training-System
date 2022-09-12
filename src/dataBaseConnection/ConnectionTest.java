package dataBaseConnection;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import knowledgeBase.LoadFiles;

public class ConnectionTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException{
		try {
			try {
				ConnectionClass conect = new ConnectionClass("localhost", "5432", "SECPROIT", "postgres", "postgres");
				conect.crateConnection();
				System.out.println("Conexión establecida..."); 
				
				LoadFiles.getDrlInfo(LoadFiles.toBytes(new File("E:/Escuela/Tesis/Ficheros de Prueba/DiezVariables.drl")), 9);
				
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
