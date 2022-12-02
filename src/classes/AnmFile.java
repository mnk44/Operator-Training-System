package classes;

import java.util.ArrayList;

public class AnmFile {
	ArrayList<Variable> variables;
	ArrayList<Cause> causes;
	ArrayList<Recomendation> recomendations;
	
	public AnmFile(ArrayList<Variable> variables, ArrayList<Cause> causes,
			ArrayList<Recomendation> recomendations) {
		super();
		this.variables = variables;
		this.causes = causes;
		this.recomendations = recomendations;
	}
	
	public ArrayList<Variable> getVariables() {
		return variables;
	}
	public void setVariables(ArrayList<Variable> variables) {
		this.variables = variables;
	}
	public ArrayList<Cause> getCauses() {
		return causes;
	}
	public void setCauses(ArrayList<Cause> causes) {
		this.causes = causes;
	}
	public ArrayList<Recomendation> getRecomendations() {
		return recomendations;
	}
	public void setRecomendations(ArrayList<Recomendation> recomendatons) {
		this.recomendations = recomendatons;
	}
}
