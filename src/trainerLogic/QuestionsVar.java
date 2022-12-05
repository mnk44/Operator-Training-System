package trainerLogic;

import java.util.ArrayList;

import classes.Variable;

public class QuestionsVar {
	ArrayList<Variable> variables;
	ArrayList<Variable> answer;
	ArrayList<String> states;
	
	public QuestionsVar(ArrayList<Variable> variables,
			ArrayList<Variable> answer, ArrayList<String> states) {
		super();
		this.variables = variables;
		this.answer = answer;
		this.states = states;
	}
	
	public ArrayList<String> getStates() {
		return states;
	}
	public void setStates(ArrayList<String> states) {
		this.states = states;
	}
	public ArrayList<Variable> getVariables() {
		return variables;
	}
	public void setVariables(ArrayList<Variable> variables) {
		this.variables = variables;
	}
	public ArrayList<Variable> getAnswer() {
		return answer;
	}
	public void setAnswer(ArrayList<Variable> answer) {
		this.answer = answer;
	}
}
