package knowledgeBase;

import java.sql.SQLException;
import java.util.ArrayList;

import systemClass.Cause;
import systemClass.CauseRecomendation;
import systemClass.Recomendation;
import systemClass.Variable;
import systemClass.VariableCause;
import systemServices.CauseService;
import systemServices.RecomendationService;
import systemServices.RulesService;
import systemServices.VariableService;

public class ReadRules {

	public static void readRules (ArrayList<String> rule, int process_id) throws SQLException{
		Variable variable = null;
		String state = null;
		Cause cause = null;
		Recomendation recomendation = null;

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

			variable = (Variable) VariableService.findName(variable_name, process_id);

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

			switch (line) {
			case "ALTO":
				state = "Alto";
				break;
			case "BAJO":
				state = "Bajo";
				break;
			case "NORMAL":
				state = "Normal";
				break;
			case "ABIERTA":
				state = "Abierto";
				break;
			case "CERRADA":
				state = "Cerrado";
				break;
			case "NEGATIVO":
				state = "Negativo";
				break;
			case "POSITIVO":
				state = "Positivo";
				break;
			}
			
			//////////////////////////////////////////////////////////////
			
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
				
				cause = (Cause) CauseService.findName(causeLine, process_id);
				VariableCause varCause = new VariableCause(variable.getVariable_id(), state, cause.getCause_id());
				Object result = RulesService.newVariableCause(varCause);
				if(result != null){
					System.out.println(result);
				}
			}
		}
		
		//get cause-recomendation
		else if(rule.get(1).contains("Causa")){
			String line = rule.get(1);
			int position = line.indexOf("matches ");
			line = line.substring(position);
			line = line.replace("matches ", "");
			line = line.substring(1, line.length()-3);
			
			cause = (Cause) CauseService.findName(line, process_id);
			
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
				
				recomendation = (Recomendation) RecomendationService.findName(recLine, process_id);
				CauseRecomendation causeRecomendation = new CauseRecomendation(cause.getCause_id(), 
						recomendation.getRecomendation_id());
				Object result = RulesService.newCauseRecomendation(causeRecomendation);
				if(result != null){
					System.out.println(result);
				}
			}
		}
	}
}
