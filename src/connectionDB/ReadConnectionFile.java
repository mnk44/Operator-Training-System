package connectionDB;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Con esta clase se lee el archivo de conexion para obtener las propiedades
 * dela conexion con la base de datos
 * 
 * @author HP
 *
 */

public class ReadConnectionFile {
	
	@SuppressWarnings("resource")
	public static LinkedList<String> connectionProperties() throws IOException{
		LinkedList<String> properties = new LinkedList<String>();

		java.io.BufferedReader fi =	new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream("src/ConnectionProperties"),"utf-8"));
		
		String current = fi.readLine();         //capturo la linea
		properties.add(current);               //guardo el host en la posicion 0
		
		current = fi.readLine();               
		properties.add(current);              //guardo el port en la posicion 1
		
		current = fi.readLine();               
		properties.add(current);             //guardo el database en la posicion 2
		
		current = fi.readLine();               
		properties.add(current);            //guardo el user en la posicion 3
		
		current = fi.readLine();               
		properties.add(current);           //guardo el password en la posicion 4
		
		return properties;
	}
}
