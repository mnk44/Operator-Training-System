package trainerLogic;

import java.util.ArrayList;

public class QuestionCause {
	ArrayList<String> causes;
	ArrayList<Integer> answer;
	ArrayList<String> variables;
	
	public QuestionCause(ArrayList<String> causes,
			ArrayList<Integer> answer, ArrayList<String> variables) {
		super();
		this.variables = variables;
		this.answer = answer;
		this.causes = causes;
	}
	
	public ArrayList<String> getCauses() {
		return causes;
	}
	public void setCauses(ArrayList<String> states) {
		this.causes = states;
	}
	public ArrayList<String> getVariables() {
		return variables;
	}
	public void setVariables(ArrayList<String> variables) {
		this.variables = variables;
	}
	public ArrayList<Integer> getAnswer() {
		return answer;
	}
	public void setAnswer(ArrayList<Integer> answer) {
		this.answer = answer;
	}
}
