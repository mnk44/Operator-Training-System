package classes;

import java.util.ArrayList;

public class DrlFile {
	ArrayList<VariableCause> varCause;
	ArrayList<CauseRecomendation> causeRec;
	
	public DrlFile(ArrayList<VariableCause> varCause,
			ArrayList<CauseRecomendation> causeRec) {
		super();
		this.varCause = varCause;
		this.causeRec = causeRec;
	}
	
	public ArrayList<VariableCause> getVarCause() {
		return varCause;
	}
	public void setVarCause(ArrayList<VariableCause> varCause) {
		this.varCause = varCause;
	}
	public ArrayList<CauseRecomendation> getCauseRec() {
		return causeRec;
	}
	public void setCauseRec(ArrayList<CauseRecomendation> causeRec) {
		this.causeRec = causeRec;
	}
}
