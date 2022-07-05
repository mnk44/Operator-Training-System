package drools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import utils.VariableType;
import contentClass.Cause;
import contentClass.Recomendation;
import contentClass.Results;
import contentClass.Variable;

public class EvaluateProcessDrool {

	private static EvaluateProcessDrool drool;
	private List<Variable> variables;
	private List<Cause> causes;
	private List<Recomendation> recomendations;
	public boolean uploaded_file = false;
	public static List <Results> current_results = new ArrayList<Results>();
	public List <Results> history;


	public EvaluateProcessDrool(){
		super();

		variables = new LinkedList<Variable>();
		causes = new LinkedList<Cause>();
		recomendations = new LinkedList<Recomendation>();
		current_results = new ArrayList<Results>();
		history = new ArrayList<Results>();
	}

	public static EvaluateProcessDrool getPrincipal(){
		if(drool == null){
			drool = new EvaluateProcessDrool();
		}

		return drool;    
	}

	public static void addResults(Results result){
		current_results.add(result);
	}


	public List<Variable> getVariables(){
		return variables;    
	}

	public List<Cause> getCauses(){ 	  
		return causes;
	}

	public List<Recomendation> getRecomendations(){
		return recomendations; 
	}


	//Buscar una variable por su nombre
	public Variable findVarByName(String name){
		for(Variable var : variables){
			if(var.getVariable_name().equals(name)){ 
				return var;
			}         
		}        
		return null;     
	}

	//Buscar una causa por su descripcion
	public Cause findCause(String data){       
		for(Cause c : causes){
			if(c.getDescription().equals(data.trim())){
				return c;
			}          
		}          
		return null;     
	}

	//Buscar recomendacion por su descripcion
	public Recomendation findRecomendation(String data){
		for(Recomendation r : recomendations){
			if(r.getDescription().equals(data.trim())){                
				return r;
			}
		}
		return null;
	}

	//Buscar una causa dentro de una lista que se pasa por parametro
	public Cause findCause(String data, List<Cause> list){         
		for(Cause c : list){
			if(c.getDescription().equals(data.trim())){                
				return c;            
			}        
		}       
		return null;
	}

	//Buscar una variable dentro de una lista que se pasa por parametro
	public Variable findVarByName(String data, List<Variable> list){         
		for(Variable var : list){
			if(var.getVariable_name().equals(data.trim())){                
				return var;            
			}        
		}       
		return null;
	}

	//Salvar un Juego de Datos.
	public void SaveFile(File file) throws IOException{
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(getVariables());
		oos.writeObject(getCauses());
		oos.close();    
	}

	//Cargar un Juego de Datos.
	public void LoadFile(File file) throws IOException{
		LinkedList<Variable> listVar = new LinkedList<>();
		LinkedList<Cause> listCause = new LinkedList<>();
		LinkedList<Recomendation> listRec = new LinkedList<>();

		try {
			java.io.BufferedReader fi =	new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(file.getPath()),"utf-8"));	
			String current = fi.readLine();
			while(!current.equalsIgnoreCase("*causa")){
				String arr[] = current.split(",");
				String name = arr[0];                  //aqui se obtiene el nombre de la variable
				String type = arr[1];                 //aqui se obtiene el tipo de variable
				Variable temporal = new Variable();
				switch (type) {
				case "Continua":
					temporal = new Variable(name, VariableType.CONTINUA);
					break;
				case "Discreta":
					temporal = new Variable(name, VariableType.DISCRETA);
					break;
				case "Valvula":
					temporal = new Variable(name, VariableType.VALVULA);
					break;
				}
				listVar.add(temporal);
				current=fi.readLine();               //aqui se guarda la variable
			}

			current =fi.readLine();
			while(!current.equalsIgnoreCase("*recomendacion")){
				Cause c = new Cause(current);
				listCause.add(c);
				current = fi.readLine();             //aqui se guarda la causa
			}

			current =fi.readLine();
			while(!current.equals("-1")){
				Recomendation r = new Recomendation(current);
				listRec.add(r);
				current = fi.readLine();             //aqui se guarda la recomendacion
			}
			fi.close();
			variables = listVar;
			causes = listCause;
			recomendations = listRec;
		} catch (IOException ex) {  			
			ex.printStackTrace();  		
		}
		uploaded_file = true;
	}

//Sobreescribir reglas
	public static void overriteRULES(String file, String content){
		try {
			File F = new File(file);
			PrintWriter pr = new PrintWriter(F);
			pr.println(content);
			pr.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	//Importar reglas  
	public static void importRULES(String file, String fileToWritte,String newName){
		try {
			FileInputStream fi = new java.io.FileInputStream(file);
			int longbytes = fi.available();
			byte[] arr = new byte[longbytes];
			int cant = fi.read(arr);

			if(cant != -1){
				String temp = "//_nombre:"+newName+"_end;\n";
				String cont = new String(arr);
				String resul = temp + cont;
				overriteRULES(fileToWritte, resul);
			}
			fi.close(); 

		} catch (IOException ex) {             
			ex.printStackTrace();          
		}     
	}

}
