package knowledgeBase;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import systemClass.FactoryProcess;
import systemServices.FactoryProcessService;

public class LoadData {
	
	public static void loadData(int process_id) throws SQLException, UnsupportedEncodingException, FileNotFoundException, ClassNotFoundException, IOException{
		FactoryProcess process = (FactoryProcess) FactoryProcessService.findId(process_id);

		File file = (File) toObject(process.getProcess_anm());
		FileReader fr = new FileReader(file);
		BufferedReader fi = new BufferedReader(fr);	
		String current = fi.readLine();
		
		while(!current.equalsIgnoreCase("*causa")){
			String arr[] = current.split(",");
			String name = arr[0];                  
			String type = arr[1];                 
			
			if(type.equalsIgnoreCase("Discreta"))
				UtilsServices.createVariable(name, VariableType.DISCRETA, 0, 0, id_process);
			else if(type.contains("Continua")){
				String min = type.replace("Continua", "");
				String max = arr[2];
				UtilsServices.createVariable(name, VariableType.CONTINUA, Integer.parseInt(max), Integer.parseInt(min), id_process);
			}else
				UtilsServices.createVariable(name, VariableType.VALVULA, 0, 0, id_process);
			
			current=fi.readLine();
		}

		current =fi.readLine();
		while(!current.equalsIgnoreCase("*recomendacion")){
			UtilsServices.createCause(current, id_process);
			current = fi.readLine();             
		}

		current =fi.readLine();
		while(!current.equals("-1")){
			UtilsServices.createRec(current, id_process);
			current = fi.readLine();             
		}
		fi.close();
	}
	
	public static byte[] toBytes(Object object) throws IOException{ 
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		ObjectOutputStream oos = new ObjectOutputStream(baos); 
		oos.writeObject(object); 

		return baos.toByteArray(); 
	} 
	
	public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException{ 
		Object object = null; 
		object = new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject(); 

		return object; 
	}
}
