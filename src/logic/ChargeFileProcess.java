package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import services.ProcessService;
import services.UtilsServices;
import utils.VariableStatus;
import utils.VariableType;
import contentClass.Process;

public class ChargeFileProcess {

	public static void chargeAnmBD(int id_process) throws SQLException, UnsupportedEncodingException, FileNotFoundException, ClassNotFoundException, IOException{
		Process process = ProcessService.findById(id_process);

		File file = (File) Convert.toObject(process.getAnm_file());
		FileReader fr = new FileReader(file);
		BufferedReader fi = new BufferedReader(fr);	
		String current = fi.readLine();
		while(!current.equalsIgnoreCase("*causa")){
			String arr[] = current.split(",");
			String name = arr[0];                  //aqui se obtiene el nombre de la variable
			String type = arr[1];                 //aqui se obtiene el tipo de variable
			
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

	public static void chargeDrlBD(int id_process) throws SQLException, UnsupportedEncodingException, FileNotFoundException, ClassNotFoundException, IOException{
		Process process = ProcessService.findById(id_process);

		File file = (File) Convert.toObject(process.getDrl_file());
		FileReader fr = new FileReader(file);
		@SuppressWarnings("resource")
		BufferedReader fi = new BufferedReader(fr);	
		String current = fi.readLine();
		
		int rule = -1;
		
		while(rule == -1){
			rule = current.indexOf("rule");
			current = fi.readLine();
		}
		
		int id_var = 0;
		String state = null;
		ArrayList<Integer> id_causes = new ArrayList<>();
		int save = -1;
		
		while(!current.replace(" ", "").equalsIgnoreCase("when")){
			
			if(current.contains("when (")){
				String varName;
				VariableType type;
				
				String linea = current.replace("when (", "");
				int position = linea.indexOf("(");
				String substring = linea.substring(0,position);   //aqui obtengo el tipo de variable
				linea = linea.substring(position);
				
				switch (substring) {
				case "VariableContinua":
					type = VariableType.CONTINUA;
					break;
				case "VariableDiscreta":
					type = VariableType.DISCRETA;
					break;
				default:
					type = VariableType.VALVULA;
					break;
				}
				
				position = linea.indexOf(",");
				substring = linea.substring(0,position);
				if(type == VariableType.CONTINUA){
					substring = substring.replace("(nombreVar  matches ", "");
				}else{
					substring = substring.replace("(nombreVar matches  ", ""); 
				}
				varName = substring.substring(1,substring.length() - 1); //aqui obtengo el nombre de la variable
				linea = linea.substring(position);
				
				position = linea.indexOf(".");
				linea = linea.substring(position + 1);
				if(type == VariableType.CONTINUA){
					position = linea.indexOf(",");
				}else{
					position = linea.indexOf(")");
				}
				
				state = linea.substring(0, position); //aqui obtengo el estado
				
				id_var = UtilsServices.findVar(varName, id_process);
				
				save++;
				
			}else if(current.contains("ProcesarDatos.insertarRespueta(")){
				id_causes = new ArrayList<>();
				String causas[] = null;
				String causa = null;
				
				String linea = current.replace("ProcesarDatos.insertarRespueta(", "");
				linea = linea.substring(1,linea.length()-3);
				System.out.println(linea);
				if(linea.contains(",")){
					causas = linea.split(",");
				}else{
					causa = linea;
				}
				
				if(causa == null){
					for(int i=0; i<causas.length; i++){
						id_causes.add(UtilsServices.findCause(causas[i], id_process));
					}
				}else{
					id_causes.add(UtilsServices.findCause(causa, id_process));
				}
				
				save++;
			}
			
			if(save == 1){
				for(int i=0; i<id_causes.size(); i++){
					System.out.println(id_var + " " + id_causes.get(i) + " " + state);
					UtilsServices.createRuleVC(id_var, id_causes.get(i), state); //llenando regla variable causa
				}
				save = -1;
			}
			
			current = fi.readLine();
		}		
		
	}
}
