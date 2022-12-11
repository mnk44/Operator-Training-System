package trainerLogic;

import java.util.ArrayList;

public class QuestionVar2 {
	ArrayList<String> variables;
	ArrayList<String> answers;
	
	public QuestionVar2(ArrayList<String> variables, ArrayList<String> answers) {
		super();
		this.variables = variables;
		this.answers = answers;
	}
	
	public ArrayList<String> getVariables() {
		return variables;
	}
	public void setVariables(ArrayList<String> variables) {
		this.variables = variables;
	}
	public ArrayList<String> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}
}
