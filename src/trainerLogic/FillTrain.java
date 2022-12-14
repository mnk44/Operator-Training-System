package trainerLogic;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import services.FileService;
import classes.Cause;
import classes.CauseRecomendation;
import classes.Process;
import classes.Recomendation;
import classes.Variable;
import classes.VariableCause;

public class FillTrain {

	public static QuestionsVar fillQuestionVar(Process process){
		ArrayList<Variable> variables = FileService.getVariables(process.getProcess_id());
		ArrayList<VariableCause> varCauses = FileService.getVariableCause(process.getProcess_id());
		int cant = (int) getRandomIntegerBetweenRange(0, 7);

		ArrayList<Variable> questions = new ArrayList<>();
		ArrayList<Integer> answers = new ArrayList<>();
		ArrayList<String> states = new ArrayList<>();

		ArrayList<Integer> numbers = new ArrayList<>();
		for(int i=0; i<variables.size(); i++){
			numbers.add(i);
		}
		Collections.shuffle(numbers);

		DecimalFormat format = new DecimalFormat("#.00");

		//fill questions
		for(int i=0; i<10; i++){
			int position = numbers.get(i);
			questions.add(variables.get(position));
		}

		//find answers
		for(int i=0; i<questions.size(); i++){
			String state = getState(varCauses, questions.get(i));
			if(state.equals("todos")){
				answers.add(i);
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
						states.add(format.format(valor));
					}else if(r == 1){
						valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() - 1000), questions.get(i).getMin_value());
						states.add(format.format(valor));
					}else{
						valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() + 1), questions.get(i).getMax_value());
						states.add(format.format(valor));
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
					answers.add(i);
					if(!questions.get(i).getVar_type().equals("Continua")){
						states.add(options[0]);
					}else{
						if(options[0].equals("ALTO")){
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMax_value() + 1), (questions.get(i).getMax_value() * 100));
							states.add(format.format(valor));
						}else{
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() - 1000), questions.get(i).getMin_value());
							states.add(format.format(valor));
						}
					}
				}else{
					if(questions.get(i).getVar_type().equals("Continua")){
						if(!options[0].equals("ALTO") && !options[1].equals("ALTO")){
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMax_value() + 1), (questions.get(i).getMax_value() * 100));
							states.add(Double.toString(valor));
						}else if(!options[0].equals("NORMAL") && !options[1].equals("NORMAL")){
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() + 1), questions.get(i).getMax_value());
							states.add(format.format(valor));
						}else{
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() - 1000), questions.get(i).getMin_value());
							states.add(format.format(valor));
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
					answers.add(i);
					if(!questions.get(i).getVar_type().equals("Continua")){
						states.add(state);
					}else{
						if(state.equals("ALTO")){
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMax_value() + 1), (questions.get(i).getMax_value() * 100));
							states.add(format.format(valor));
						}else if(state.equals("BAJO")){
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() - 1000), questions.get(i).getMin_value());
							states.add(format.format(valor));
						}else{
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() + 1), questions.get(i).getMax_value());
							states.add(format.format(valor));
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
							states.add(format.format(valor));
						}else{
							double valor = getRandomIntegerBetweenRange((questions.get(i).getMin_value() + 1), questions.get(i).getMax_value());
							states.add(format.format(valor));
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

	public static QuestionCause fillQuestionCause(Process process){
		ArrayList<Integer> answers = new ArrayList<>();
		ArrayList<String> questions = new ArrayList<>();
		ArrayList<String> causes = new ArrayList<>();

		ArrayList<Variable> variables = FileService.getVariables(process.getProcess_id());
		ArrayList<Cause> causeList = FileService.getCauses(process.getProcess_id());
		ArrayList<VariableCause> varCauses = FileService.getVariableCause(process.getProcess_id());

		ArrayList<Integer> azar = new ArrayList<>();

		DecimalFormat format = new DecimalFormat("#.00");

		for(int i=0; i<5; i++){
			answers.add(-1);
			questions.add("");
			azar.add(i);
		}
		Collections.shuffle(azar);

		for(int i=0; i<5; i++){
			int position = (int) getRandomIntegerBetweenRange(0, (varCauses.size() - 1));
			Variable var = findVar(variables, varCauses.get(position).getVar_id());
			String qt = var.getVar_name();
			if(var.getVar_type().equals("Continua")){
				if(varCauses.get(position).getState_var().equals("ALTO")){
					double valor = getRandomIntegerBetweenRange((var.getMax_value() + 1), (var.getMax_value() * 100));
					qt = qt + " con un valor " + format.format(valor);
				}else if(varCauses.get(position).getState_var().equals("NORMAL")){
					double valor = getRandomIntegerBetweenRange(var.getMin_value(), var.getMax_value());
					qt = qt + " con un valor " + format.format(valor);
				}else{
					double valor = getRandomIntegerBetweenRange((var.getMin_value() - 1000), var.getMin_value());
					qt = qt + " con un valor " + format.format(valor);
				}
			}else{
				qt = qt + " en estado " + varCauses.get(position).getState_var();
			}
			questions.set(azar.get(i), qt);

			String c = findCause(causeList, varCauses.get(position).getCause_id());
			if(causes.contains(c)){
				answers.set(azar.get(i), causes.indexOf(c));
			}else{
				causes.add(c);
				answers.set(azar.get(i), (causes.size()-1));
			}
		}

		QuestionCause q = new QuestionCause(causes, answers, questions);
		return q;
	}

	public static QuestionCause fillQuestionCause2(Process process){
		ArrayList<Integer> answers = new ArrayList<>();
		ArrayList<String> questions = new ArrayList<>();
		ArrayList<String> causes = new ArrayList<>();
		ArrayList<String> ccc = new ArrayList<>();

		int index = -1;
		int other = -1;
		ArrayList<Integer> repit = new ArrayList<>();

		ArrayList<Variable> variables = FileService.getVariables(process.getProcess_id());
		ArrayList<Cause> causeList = FileService.getCauses(process.getProcess_id());
		ArrayList<VariableCause> varCauses = FileService.getVariableCause(process.getProcess_id());

		ArrayList<Integer> azar = new ArrayList<>();

		DecimalFormat format = new DecimalFormat("#.00");

		for(int k=0; k<2; k++){
			index = causes.size();
			int position = (int) getRandomIntegerBetweenRange(0, (varCauses.size() - 1));
			Variable var = findVar(variables, varCauses.get(position).getVar_id());
			String qt = var.getVar_name();
			if(var.getVar_type().equals("Continua")){
				if(varCauses.get(position).getState_var().equals("ALTO")){
					double valor = getRandomIntegerBetweenRange((var.getMax_value() + 1), (var.getMax_value() * 100));
					qt = qt + " con un valor " + format.format(valor);
				}else if(varCauses.get(position).getState_var().equals("NORMAL")){
					double valor = getRandomIntegerBetweenRange(var.getMin_value(), var.getMax_value());
					qt = qt + " con un valor " + format.format(valor);
				}else{
					double valor = getRandomIntegerBetweenRange((var.getMin_value() - 1000), var.getMin_value());
					qt = qt + " con un valor " + format.format(valor);
				}
			}else{
				qt = qt + " en estado " + varCauses.get(position).getState_var();
			}
			questions.add(qt);

			for(int i=0; i<varCauses.size(); i++){
				if(varCauses.get(i).getVar_id() == var.getVar_id()){
					String caus = findCause(causeList, varCauses.get(i).getCause_id());
					if(!causes.contains(caus)){
						causes.add(caus);
						System.out.println(caus);
					}else{
						repit.add(causes.indexOf(caus));
					}
				}
			}
			System.out.println(index);
		}

		if(causes.size() <= 5){
			other = causes.size();
			for(int i=(causes.size()-1); i<5; i++){
				int position = (int) getRandomIntegerBetweenRange(0, (varCauses.size() - 1));
				String caus = findCause(causeList, varCauses.get(position).getCause_id());
				if(!causes.contains(caus)){
					causes.add(caus);
				}else{
					i = i - 1;
				}
			}
			for(int i=0; i<5; i++){
				azar.add(i);
			}
			Collections.shuffle(azar);
		}else if(causes.size() > 5){
			for(int i=0; i<causes.size(); i++){
				azar.add(i);
			}
			Collections.shuffle(azar);
		}
		for(int i=0; i<5; i++){
			ccc.add(causes.get(azar.get(i)));
			if(repit.contains(azar.get(i))){
				answers.add(3);
			}else if(azar.get(i) < index){
				answers.add(1);
			}else if(azar.get(i) < other && other != -1){
				answers.add(2);
			}else{
				answers.add(0);
			}
		}

		QuestionCause q = new QuestionCause(ccc, answers, questions);
		return q;
	}

	private static Variable findVar(ArrayList<Variable> variables, int id){
		Variable variable = null;
		int cont = 0;

		while((variable == null) && (cont < variables.size())){
			int idVar = variables.get(cont).getVar_id();
			if(idVar == id){
				variable = variables.get(cont);
			}
			cont = cont + 1;
		}

		return variable;
	}

	private static String findCause(ArrayList<Cause> variables, int id){
		String variable = null;
		int cont = 0;

		while(variable == null && cont < variables.size()){
			int idVar = variables.get(cont).getCause_id();
			if(idVar == id){
				variable = variables.get(cont).getCause_name();
			}
			cont = cont + 1;
		}

		return variable;
	}
	
	private static String findRec(ArrayList<Recomendation> variables, int id){
		String variable = null;
		int cont = 0;

		while(variable == null && cont < variables.size()){
			int idVar = variables.get(cont).getRec_id();
			if(idVar == id){
				variable = variables.get(cont).getRec_name();
			}
			cont = cont + 1;
		}

		return variable;
	}

	public static QuestionVar2 fillQuestionVar2(Process process){
		ArrayList<Variable> variables = FileService.getVariables(process.getProcess_id());
		ArrayList<VariableCause> varCauses = FileService.getVariableCause(process.getProcess_id());

		ArrayList<String> questions = new ArrayList<>();
		ArrayList<String> answers = new ArrayList<>();

		ArrayList<Integer> azar = new ArrayList<>();

		for(int i=0; i<10; i++){
			answers.add("");
			questions.add("");
			azar.add(i);
		}
		Collections.shuffle(azar);

		//fill questions
		for(int i=0; i<10; i++){
			int position = (int) getRandomIntegerBetweenRange(0, (varCauses.size() - 1));
			Variable var = findVar(variables, varCauses.get(position).getVar_id());
			String state = getState(varCauses,  var);
			questions.set(azar.get(i), var.getVar_name());
			if(state.equals("todos")){
				if(var.getVar_type().equals("Discreta")){
					answers.set(azar.get(i), "POSITIVO,NEGATIVO");
				}else if(var.getVar_type().equals("Continua")){
					answers.set(azar.get(i), "ALTO,BAJO,NORMAL");
				}else{
					answers.set(azar.get(i), "ABIERTA,CERRADA,NORMAL");
				}
			}else{
				answers.set(azar.get(i), state);
			}
		}

		QuestionVar2 q = new QuestionVar2(questions, answers);	
		return q;
	}

	public static QuestionCause fillQuestionCause3(Process process){
		ArrayList<Integer> answers = new ArrayList<>();
		ArrayList<String> questions = new ArrayList<>();
		ArrayList<String> causes = new ArrayList<>();

		ArrayList<Variable> variables = FileService.getVariables(process.getProcess_id());
		ArrayList<Cause> causeList = FileService.getCauses(process.getProcess_id());
		ArrayList<VariableCause> varCauses = FileService.getVariableCause(process.getProcess_id());

		ArrayList<Integer> azar = new ArrayList<>();

		DecimalFormat format = new DecimalFormat("#.00");

		for(int i=0; i<5; i++){
			answers.add(-1);
			questions.add("");
			causes.add("");
			azar.add(i);
		}
		Collections.shuffle(azar);

		int real = (int) getRandomIntegerBetweenRange(0, 5);

		int x = -1;
		int y = -1;

		for(int i=0; i<5; i++){
			x = (int) getRandomIntegerBetweenRange(0, (varCauses.size() - 1));
			y = (int) getRandomIntegerBetweenRange(0, (varCauses.size() - 1));

			Variable v = findVar(variables, varCauses.get(x).getVar_id());
			String qt = v.getVar_name();
			if(v.getVar_type().equals("Continua")){
				if(varCauses.get(x).getState_var().equals("ALTO")){
					double valor = getRandomIntegerBetweenRange((v.getMax_value() + 1), (v.getMax_value() * 100));
					qt = qt + " con un valor " + format.format(valor);
				}else if(varCauses.get(x).getState_var().equals("NORMAL")){
					double valor = getRandomIntegerBetweenRange(v.getMin_value(), v.getMax_value());
					qt = qt + " con un valor " + format.format(valor);
				}else{
					double valor = getRandomIntegerBetweenRange((v.getMin_value() - 1000), v.getMin_value());
					qt = qt + " con un valor " + format.format(valor);
				}
			}else{
				qt = qt + " en estado " + varCauses.get(x).getState_var();
			}
			questions.set(azar.get(i), qt);

			if(real > 0){
				String cause = findCause(causeList, varCauses.get(x).getCause_id());
				causes.set(azar.get(i), cause);
				answers.set(azar.get(i), 1);
			}else{
				int r = isCause(v.getVar_id(), varCauses.get(y).getCause_id(), varCauses);
				answers.set(azar.get(i), r);
				String cause = findCause(causeList, varCauses.get(y).getCause_id());
				causes.set(azar.get(i), cause);
			}
			real = real - 1;
		}

		QuestionCause q = new QuestionCause(causes, answers, questions);
		return q;
	}

	private static int isCause(int var, int cause, ArrayList<VariableCause> varCause){
		int resp = 2;
		int i = 0;

		while(resp == 2 && i < varCause.size()){
			if(varCause.get(i).getVar_id() == var && varCause.get(i).getCause_id() == cause){
				resp = 1;
			}
			i = i + 1;
		}

		return resp;
	}
	
	private static int isRec(int var, int cause, ArrayList<CauseRecomendation> varCause){
		int resp = 2;
		int i = 0;

		while(resp == 2 && i < varCause.size()){
			if(varCause.get(i).getRec_id() == var && varCause.get(i).getCause_id() == cause){
				resp = 1;
			}
			i = i + 1;
		}

		return resp;
	}

	public static QuestionRec fillQuestionRec(Process p) {
		ArrayList<Integer> answers = new ArrayList<>();
		ArrayList<String> recomendations = new ArrayList<>();
		ArrayList<String> causes = new ArrayList<>();

		ArrayList<Recomendation> rec = FileService.getRec(p.getProcess_id());
		ArrayList<Cause> causeList = FileService.getCauses(p.getProcess_id());
		ArrayList<CauseRecomendation> causesRec = FileService.getCauseRec(p.getProcess_id());

		ArrayList<Integer> azar = new ArrayList<>();

		for(int i=0; i<5; i++){
			answers.add(-1);
			recomendations.add("");
			causes.add("");
			azar.add(i);
		}
		Collections.shuffle(azar);

		int real = (int) getRandomIntegerBetweenRange(0, 5);
		int x = -1;
		int y = -1;
		
		for(int i=0; i<5; i++){
			x = (int) getRandomIntegerBetweenRange(0, (causesRec.size() - 1));
			y = (int) getRandomIntegerBetweenRange(0, (causesRec.size() - 1));

			String v = findCause(causeList, causesRec.get(x).getCause_id());
			causes.set(azar.get(i), v);

			if(real > 0){
				String re = findRec(rec, causesRec.get(x).getRec_id());
				recomendations.set(azar.get(i), re);
				answers.set(azar.get(i), 1);
			}else{
				int r = isRec(causesRec.get(x).getCause_id(), causesRec.get(y).getCause_id(), causesRec);
				answers.set(azar.get(i), r);
				String re = findRec(rec, causesRec.get(x).getRec_id());
				recomendations.set(azar.get(i), re);
			}
			real = real - 1;
		}

		QuestionRec r = new QuestionRec(causes, answers, recomendations);
		return r;
	}

}
