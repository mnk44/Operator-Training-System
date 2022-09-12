package knowledgeBase;

import java.sql.SQLException;
import java.util.ArrayList;

import systemClass.Cause;
import systemClass.Recomendation;
import systemClass.Variable;
import systemClass.VariableCause;
import systemServices.CauseService;
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
		}

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
			int position = causeLine.indexOf("String(");
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
		
		//get cause-recomendation
	}
}
