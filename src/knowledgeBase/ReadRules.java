package knowledgeBase;

import java.util.ArrayList;

import classes.AnmFile;
import classes.Cause;
import classes.CauseRecomendation;
import classes.DrlFile;
import classes.Recomendation;
import classes.Variable;
import classes.VariableCause;

public class ReadRules {

	public static DrlFile readRules (ArrayList<String> rule, int process_id, AnmFile anm, DrlFile drlFile){
		Variable variable = null;
		String state = null;
		Cause cause = null;
		ArrayList<Cause> causesList = new ArrayList<>();
		Recomendation recomendation = null;
		
		ArrayList<VariableCause> drlVar = drlFile.getVarCause();
		ArrayList<CauseRecomendation> drlCaus = drlFile.getCauseRec();

		//get variable-cause
		if(rule.get(0).contains("VariableContinua") || rule.get(0).contains("VariableDiscreta") ||
				rule.get(0).contains("Valvula")){
			String line = rule.get(0);
			int position = line.indexOf("matches ");
			line = line.substring(position);

			String variable_name;
			position = line.indexOf(",");
			String line_name = line.substring(0, position);
			line = line.substring(position);

			if(rule.get(0).contains("VariableContinua")){
				variable_name = line_name.substring(9);
				variable_name = variable_name.substring(0, variable_name.length()-1);
			}else{
				variable_name = line_name.substring(10);
				variable_name = variable_name.substring(0, variable_name.length()-1);
			}

			variable = findVar(anm, variable_name);
			if(variable == null){
				System.out.println("variable-causa: no se encontró una variable -- " + variable_name);
				return null;
			}

			position = line.indexOf("Estados."); //state
			line = line.substring(position);
			line = line.replace("Estados.", "");

			if(rule.get(0).contains("VariableContinua")){
				position = line.indexOf(",");
				line = line.substring(0,position);
				line = line.replace(",", "");
			}else{
				position = line.indexOf(")");
				line = line.substring(0,position);
				line = line.replace(")", "");
			}
			
			state = line;

			boolean causes = false; //cause
			ArrayList<Integer> positionCause = new ArrayList<>();
			for(int i=0; i<rule.size(); i++){
				if(rule.get(i).equals(" tree.insertNode(nodeA, root);")){
					causes = true;
				}else if(rule.get(i).contains("new String") && causes){
					positionCause.add(i);
				}
			}

			for(int i=0; i<positionCause.size(); i++) {
				String causeLine = rule.get(positionCause.get(i));
				position = causeLine.indexOf("String(");
				causeLine = causeLine.substring(position);
				causeLine = causeLine.replace("String(", "");
				position = causeLine.indexOf(")");
				causeLine = causeLine.substring(0,position);
				causeLine = causeLine.replace(")", "");
				causeLine = causeLine.substring(1,causeLine.length()-1);
				
				cause = findCause(anm, causeLine);
				if(cause == null){
					System.out.println("variable-causa: no se encontró una causa -- " + causeLine);
					return null;
				}

				VariableCause varCause = new VariableCause(process_id, variable.getVar_id(), state, cause.getCause_id());
				drlVar.add(varCause);
			}
		}

		//get conjunta
		else if(rule.get(1).contains("Conjunta")){
			//cause
			int i = 0;
			while(!rule.get(i).contains("ProcesarDatos.insertarRespueta(")){
				i = i + 1;
			}

			String causeLine = rule.get(i);
			causeLine = causeLine.replace("ProcesarDatos.insertarRespueta(", "");
			causeLine = causeLine.substring(1,causeLine.length()-3);

			//causes
			if(causeLine.contains(",")){
				String arr[] = causeLine.split(",");
				int m = 0;
				String aux = "";
				
				while(m < arr.length){
					String name = aux + arr[m];
					cause = findCause(anm, name);
					if(cause != null){
						causesList.add(cause);
						aux = "";
					}else{
						aux = arr[m] + ",";
					}
					m = m + 1;
				}
			}else{
				cause = findCause(anm, causeLine);
				if(cause == null){
					System.out.println("conjunta: no se encontró una causa -- " + causeLine);
					return null;
				}else{
					causesList.add(cause);
				}
			}

			//variables
			int poss = 3;
			String line = rule.get(poss);
			while(!line.replace(" ", "").equals("then")){
				if(line.contains("VariableContinua") || line.contains("VariableDiscreta") ||
						line.contains("Valvula")){
					int position = line.indexOf("matches ");
					line = line.substring(position);

					String variable_name;
					position = line.indexOf(",");
					String line_name = line.substring(0, position);
					line = line.substring(position);
					if(line.contains("valorVC")){
						variable_name = line_name.substring(9);
						variable_name = variable_name.substring(0, variable_name.length()-1);
					}else{
						variable_name = line_name.substring(10);
						variable_name = variable_name.substring(0, variable_name.length()-1);
					}

					variable = findVar(anm, variable_name);
					if(variable == null){
						System.out.println("conjunta: no se encontró una variable -- " + variable_name);
						return null;
					}

					position = line.indexOf("Estados."); //state
					line = line.substring(position);
					line = line.replace("Estados.", "");

					if(line.contains("valorVC")){
						position = line.indexOf(",");
						line = line.substring(0,position);
						line = line.replace(",", "");
					}else{
						position = line.indexOf(")");
						line = line.substring(0,position);
						line = line.replace(")", "");
					}
					
					state = line;

					for(int n = 0; n < causesList.size(); n++){
						VariableCause varCause = new VariableCause(process_id, variable.getVar_id(), state, causesList.get(n).getCause_id());
						drlVar.add(varCause);
					}
				}
				poss++;
				line = rule.get(poss);
			}
		}

		//get cause-recomendation
		else if(rule.get(1).contains("Causa")){
			String line = rule.get(1);
			int position = line.indexOf("matches ");
			line = line.substring(position);
			line = line.replace("matches ", "");
			line = line.substring(1, line.length()-3);

			cause = findCause(anm, line);
			if(cause == null){
				System.out.println("causa-recomendacion: no se encontró una causa -- " + line);
				return null;
			}

			boolean rec = false; //recomendation
			ArrayList<Integer> positionRecomendation = new ArrayList<>();
			for(int i=0; i<rule.size(); i++){
				if(rule.get(i).contains("tree.insertNode(root, null);")){
					rec = true;
				}else if(rule.get(i).contains("new String") && rec){
					positionRecomendation.add(i);
				}
			}

			for(int i=0; i<positionRecomendation.size(); i++) {
				String recLine = rule.get(positionRecomendation.get(i));
				position = recLine.indexOf("String(");
				recLine = recLine.substring(position);
				recLine = recLine.replace("String(", "");
				position = recLine.indexOf(")");
				recLine = recLine.substring(0,position);
				recLine = recLine.replace(")", "");
				recLine = recLine.substring(1,recLine.length()-1);

				recomendation = findRec(anm, recLine);
				if(recomendation == null){
					System.out.println("causa-recomendacion: no se encontró una recomendacion -- " + recLine);
					return null;
				}
				CauseRecomendation causeRecomendation = new CauseRecomendation(cause.getCause_id(), recomendation.getRec_id(), process_id);
				drlCaus.add(causeRecomendation);
			}
		}
		
		DrlFile d = new DrlFile(drlVar, drlCaus);
		
		return d;
	}
	
	public static Variable findVar(AnmFile anm, String name){
		int cont = 0;
		Variable var = null;
		
		while(var == null && cont < anm.getVariables().size()){
			String varname = anm.getVariables().get(cont).getVar_name();
			if(varname.equals(name)){
				var = anm.getVariables().get(cont);
			}
			cont = cont + 1;
		}
		
		return var;
	}
	
	public static Cause findCause(AnmFile anm, String name){
		int cont = 0;
		Cause var = null;
		
		while(var == null && cont < anm.getCauses().size()){
			String varname = anm.getCauses().get(cont).getCause_name();
			if(varname.equals(name)){
				var = anm.getCauses().get(cont);
			}
			cont = cont + 1;
		}
		
		return var;
	}
	
	public static Recomendation findRec(AnmFile anm, String name){
		int cont = 0;
		Recomendation var = null;
		
		while(var == null && cont < anm.getRecomendations().size()){
			String varname = anm.getRecomendations().get(cont).getRec_name();
			if(varname.equals(name)){
				var = anm.getRecomendations().get(cont);
			}
			cont = cont + 1;
		}
		
		return var;
	}
}
