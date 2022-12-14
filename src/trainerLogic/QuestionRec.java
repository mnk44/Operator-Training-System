package trainerLogic;

import java.util.ArrayList;

public class QuestionRec {
	ArrayList<String> causes;
	ArrayList<Integer> answer;
	ArrayList<String> recomendations;

	public ArrayList<String> getCauses() {
		return causes;
	}
	public void setCauses(ArrayList<String> causes) {
		this.causes = causes;
	}
	public ArrayList<Integer> getAnswer() {
		return answer;
	}
	public void setAnswer(ArrayList<Integer> answer) {
		this.answer = answer;
	}
	public ArrayList<String> getRecomendations() {
		return recomendations;
	}
	public void setRecomendations(ArrayList<String> recomendations) {
		this.recomendations = recomendations;
	}
	
	public QuestionRec(ArrayList<String> causes, ArrayList<Integer> answer,
			ArrayList<String> recomendations) {
		super();
		this.causes = causes;
		this.answer = answer;
		this.recomendations = recomendations;
	}
}
