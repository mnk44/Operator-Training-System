package knowledgeBase;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import systemClass.Cause;
import systemClass.Recomendation;
import systemClass.Variable;
import systemEnums.VariableTypes;
import systemServices.RulesService;

public class LoadFiles {
	
	public static void getAnmInfo(byte[] anm, int process_id) throws ClassNotFoundException, IOException, SQLException{
		File anmFile = (File) toObject(anm); 
		FileReader fr = new FileReader(anmFile);
		BufferedReader fi = new BufferedReader(fr);	
		String current = fi.readLine();
		
		ArrayList<Variable> variables = new ArrayList<>();
		ArrayList<Cause> causes = new ArrayList<>();
		ArrayList<Recomendation> recomendations = new ArrayList<>();
		
		//variables
		while(!current.equalsIgnoreCase("*causa")){
			String arr[] = current.split(",");
			String name = arr[0];                  
			String type = arr[1];                 

			Variable variable;
			
			if(type.equalsIgnoreCase("Discreta")){
				variable = new Variable(name, VariableTypes.Discreta.toString(), process_id);
			}else if(type.contains("Continua")){
				String min = type.replace("Continua", "");
				String max = arr[2];
				variable = new Variable(name, VariableTypes.Continua.toString(), Integer.parseInt(min), Integer.parseInt(max), process_id);
			}else{
				variable = new Variable(name, VariableTypes.Discreta.toString(), process_id);
			}
			
			variables.add(variable);
			
			current = fi.readLine();
		}
		
		//causes
		current = fi.readLine();
		while(!current.equalsIgnoreCase("*recomendacion")){
			Cause cause = new Cause(current, process_id);
			
			causes.add(cause);
			
			current = fi.readLine();             
		}
		
		//recomendations
		current = fi.readLine();
		while(!current.equals("-1")){
			Recomendation recomendation = new Recomendation(current, process_id);
			
			recomendations.add(recomendation);
			
			current = fi.readLine();             
		}
		
		fi.close();
		
		RulesService.insertAnmInfo(variables, causes, recomendations);
	}
	
	public static void getDrlInfo(byte[] drl, int process_id) throws IOException, ClassNotFoundException, SQLException{
		File drlFile = (File) toObject(drl); 
		FileReader fr = new FileReader(drlFile);
		BufferedReader fi = new BufferedReader(fr);	
		String current = fi.readLine();
		
        while(current != null){
        	int begining = -1;
    		while(begining == -1){
    			begining = current.indexOf("rule");
    			current = fi.readLine();
    		}
    		
    		//read one rule
    		ArrayList<String> rule = new ArrayList<String>();
    		while(!current.replace(" ", "").equals("end")){
    			rule.add(current);
    			current = fi.readLine();
    		}
    		ReadRules.readRules(rule, process_id);
    		
    		current = fi.readLine();
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

