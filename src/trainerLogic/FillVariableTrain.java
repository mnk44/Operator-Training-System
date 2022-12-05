package trainerLogic;

import java.util.ArrayList;

import services.FileService;
import classes.Process;
import classes.Variable;
import classes.VariableCause;

public class FillVariableTrain {
	
	public static QuestionsVar fillQuestionVar(Process process){
		ArrayList<Variable> variables = FileService.getVariables(process.getProcess_id());
		ArrayList<VariableCause> varCauses = FileService.getVariableCause(process.getProcess_id());
		int cant = (int) getRandomIntegerBetweenRange(1, 11);
		
		ArrayList<Variable> questions = new ArrayList<>();
		ArrayList<Variable> answers = new ArrayList<>();
		ArrayList<String> states = new ArrayList<>();
		
		//fill questions
		for(int i=0; i<10; i++){
			int position = (int) getRandomIntegerBetweenRange(0, variables.size());
			questions.add(variables.get(position));
		}
		
		//find answers
		for(int i=0; i<questions.size(); i++){
			String state = getState(varCauses, questions.get(i));
			if(state.equals("todos")){
				answers.add(questions.get(i));
				if(questions.get(i).getVar_type().equals("Discreta")){
					int r = (int) getRandomIntegerBetweenRange(0, 2);
					if(r == 0){
						states.add("POSITIVO");
					}else{
						states.add("NEGATIVO");
					}
				}else if(questions.get(i).getVar_type().equals("Continua")){
					int r = (int) getRandomIntegerBetweenRange(0, 3);
					double valor;
					if(r == 0){
						valor = getRandomIntegerBetweenRange((questions.get(i).getMax_value() + 1), (questions.get(i).getMax_value() * 100));
						states.add(Double.toString(valor));
					}else if(r == 1){
						valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() - 1000), questions.get(i).getMin_value());
						states.add(Double.toString(valor));
					}else{
						valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() + 1), questions.get(i).getMax_value());
						states.add(Double.toString(valor));
					}
				}else{
					int r = (int) getRandomIntegerBetweenRange(0, 3);
					if(r == 0){
						states.add("ABIERTA");
					}else if(r == 1){
						states.add("CERRADA");
					}else{
						states.add("NORMAL");
					}
				}
			}else if(state.contains(",")){
				String[] options = state.split(",");
				if(answers.size() < cant){
					answers.add(questions.get(i));
					if(!questions.get(i).getVar_type().equals("Continua")){
						states.add(options[0]);
					}else{
						if(options[0].equals("ALTO")){
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMax_value() + 1), (questions.get(i).getMax_value() * 100));
							states.add(Double.toString(valor));
						}else{
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() - 1000), questions.get(i).getMin_value());
							states.add(Double.toString(valor));
						}
					}
				}else{
					if(questions.get(i).getVar_type().equals("Continua")){
						if(!options[0].equals("ALTO") && !options[1].equals("ALTO")){
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMax_value() + 1), (questions.get(i).getMax_value() * 100));
							states.add(Double.toString(valor));
						}else if(!options[0].equals("NORMAL") && !options[1].equals("NORMAL")){
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() + 1), questions.get(i).getMax_value());
							states.add(Double.toString(valor));
						}else{
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() - 1000), questions.get(i).getMin_value());
							states.add(Double.toString(valor));
						}
					}else{
						if(!options[0].equals("ABIERTA") && !options[1].equals("ABIERTA")){
							states.add("ABIERTA");
						}else if(!options[0].equals("CERRADA") && !options[1].equals("CERRADA")){
							
						}else{
							states.add("NORMAL");
						}
					}
				}
			}else{
				if(answers.size() < cant){
					answers.add(questions.get(i));
					if(!questions.get(i).getVar_type().equals("Continua")){
						states.add(state);
					}else{
						if(state.equals("ALTO")){
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMax_value() + 1), (questions.get(i).getMax_value() * 100));
							states.add(Double.toString(valor));
						}else if(state.equals("BAJO")){
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() - 1000), questions.get(i).getMin_value());
							states.add(Double.toString(valor));
						}else{
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() + 1), questions.get(i).getMax_value());
							states.add(Double.toString(valor));
						}
					}
				}else{
					if(questions.get(i).getVar_type().equals("Discreta")){
						if(state.equals("NEGATIVO")){
							states.add("POSITIVO");
						}else{
							states.add("NEGATIVO");
						}
					}else if(questions.get(i).getVar_type().equals("Continua")){
						if(!state.equals("ALTO")){
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMax_value() + 1), (questions.get(i).getMax_value() * 100));
							states.add(Double.toString(valor));
						}else{
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() + 1), questions.get(i).getMax_value());
							states.add(Double.toString(valor));
						}
					}else{
						if(!state.equals("ABIERTA")){
							states.add("ABIERTA");
						}else{
							states.add("NORMAL");
						}
					}
				}
			}
		}
		
		QuestionsVar q = new QuestionsVar(questions, answers, states);	
		return q;
	}
	
	private static double getRandomIntegerBetweenRange(double min, double max){
	    double x = (Math.random()*((max-min)+1))+min;
	    return x;
	}
	
	private static String getState(ArrayList<VariableCause> varCauses, Variable variable){
		String state = null;
		ArrayList<String> states = new ArrayList<>();
		ArrayList<VariableCause> var = new ArrayList<>();
		
		for(int i=0; i<varCauses.size(); i++){
			if(varCauses.get(i).getVar_id() == variable.getVar_id()){
				var.add(varCauses.get(i));
			}
		}
		
		for(int i=0; i<var.size(); i++){
			if(state == null){
				state = var.get(i).getState_var();
			}else{
				if(state != var.get(i).getState_var()){
					states.add(var.get(i).getState_var());
					states.add(state);
				}
			}
		}
		
		if(states.size() < 0){
			if(variable.getVar_type().equals("Discreta")){
				state = "todos";				
			}else if(variable.getVar_type().equals("Continua")){
				boolean alto = states.contains("ALTO");
				boolean bajo = states.contains("BAJO");
				boolean normal = states.contains("NORMAL");
				
				if(alto && bajo && normal){
					state = "todos";
				}else{
					state = "";
					if(alto){
						state = "Alto,";
					}
					if(bajo){
						state = state + "Bajo,";
					}
					if(normal){
						state = state + "Normal,";
					}
					state = state.substring(0, state.length() - 1);
				}
			}else{
				boolean abierta = states.contains("ABIERTA");
				boolean cerrada = states.contains("CERRADA");
				boolean normal = states.contains("NORMAL");
				
				if(abierta && cerrada && normal){
					state = "todos";
				}else{
					state = "";
					if(abierta){
						state = "Abierta,";
					}
					if(cerrada){
						state = state + "Cerrada,";
					}
					if(normal){
						state = state + "Normal,";
					}
					state = state.substring(0, state.length() - 1);
				}
			}
		}
		
		return state;
	}
}
