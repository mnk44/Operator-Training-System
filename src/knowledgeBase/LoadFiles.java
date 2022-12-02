package knowledgeBase;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import classes.AnmFile;
import classes.Cause;
import classes.CauseRecomendation;
import classes.DrlFile;
import classes.Recomendation;
import classes.Variable;
import classes.VariableCause;

public class LoadFiles {

	public static AnmFile getAnmInfo(File anmFile, int process_id, ArrayList<Integer> ids) throws IOException {
		FileReader fr = new FileReader(anmFile);
		BufferedReader fi = new BufferedReader(fr);	
		String current = fi.readLine();

		int varId = ids.get(0);
		int causeId = ids.get(1);
		int recId = ids.get(2);

		ArrayList<Variable> variables = new ArrayList<>();
		ArrayList<Cause> causes = new ArrayList<>();
		ArrayList<Recomendation> recomendations = new ArrayList<>();

		//variables
		while(!current.equalsIgnoreCase("*causa")){
			String arr[] = current.split(",");
			String name = arr[0];                  
			String type = arr[1];                 

			Variable variable;
			varId = varId + 1;

			if(!type.contains("Continua")){
				if(type.contains("Discreta")){
					variable = new Variable(varId, process_id, changeAcent(name), "Discreta", 0, 0);
				}else{
					variable = new Variable(varId, process_id, changeAcent(name), "Válvula", 0, 0);
				}
			}else{
				String min = type.replace("Continua", "");
				String max = arr[2];
				variable = new Variable(varId, process_id, changeAcent(name), "Continua", Float.parseFloat(min), Float.parseFloat(max));
			}

			variables.add(variable);

			current = fi.readLine();
		}

		//causes
		current = fi.readLine();
		while(!current.equalsIgnoreCase("*recomendacion")){
			causeId = causeId + 1;
			Cause cause = new Cause(causeId, process_id, changeAcent(current));
			causes.add(cause);

			current = fi.readLine();             
		}

		//recomendations
		current = fi.readLine();
		while(!current.equals("-1")){
			recId = recId + 1;
			Recomendation recomendation = new Recomendation(recId, process_id, changeAcent(current));
			recomendations.add(recomendation);

			current = fi.readLine();             
		}

		fi.close();

		AnmFile anmClass = new AnmFile(variables, causes, recomendations);
		
		return anmClass;
	}

	public static DrlFile getDrlInfo(File drlFile, int process_id, AnmFile anm) throws IOException {
		FileReader fr = new FileReader(drlFile);
		BufferedReader fi = new BufferedReader(fr);	
		String current = fi.readLine();

		DrlFile drlF = new DrlFile(new ArrayList<VariableCause>(), new ArrayList<CauseRecomendation>());

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
			try {
				drlF = ReadRules.readRules(rule, process_id, anm, drlF);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if(drlF == null){
				JOptionPane.showMessageDialog(null, "El ficehero anm y drl no son compatibles entre sí", "Error", JOptionPane.ERROR_MESSAGE);
				current = null;
			}else{
				current = fi.readLine();
				current = fi.readLine();
			}
		}

		fi.close();

		return drlF;
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

	public static String changeAcent(String w){	
		boolean finish = false;
		
		while(!finish){
			if(w.contains("Ã¡")){
				int pos = w.indexOf("Ã¡");
				w = w.substring(0, pos) + "á" + w.substring(pos + 2);
			}else if(w.contains("Ã©")){
				int pos = w.indexOf("Ã©");
				w = w.substring(0, pos) + "é" + w.substring(pos + 2);			
			}else if(w.contains("Ã­")){
				int pos = w.indexOf("Ã­");
				w = w.substring(0, pos) + "í" + w.substring(pos + 2);			
			}else if(w.contains("Ã³")){
				int pos = w.indexOf("Ã³");
				w = w.substring(0, pos) + "ó" + w.substring(pos + 2);		
			}else if(w.contains("Ã±")){
				int pos = w.indexOf("Ã±");
				w = w.substring(0, pos) + "ñ" + w.substring(pos + 2);
			}else if(w.contains("Ã")){
				int pos = w.indexOf("Ã");
				w = w.substring(0, pos) + "ú" + w.substring(pos + 2);				
			}else if(w.contains("Â")){
				int pos = w.indexOf("Â");
				w = w.substring(0, pos) + w.substring(pos + 1);
			}else{
				finish = true;
			}
		}

		return w;
	}
}

