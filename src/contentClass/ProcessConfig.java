package contentClass;

import utils.QuestionType;

public class ProcessConfig {
	
	private int id_config;
	private int process;
	private int cant_total;
	
	private int cant_var;
	private boolean cons_var;
	private QuestionType typeVar;
	
	private int cant_cause;
	private boolean cons_cause;
	private QuestionType typeCause;
	
	private int cant_rec;
	private boolean cons_rec;
	private QuestionType typeRec;
	
	public ProcessConfig(int id_config, int process, int cant_total,
			int cant_var, boolean cons_var, QuestionType typeVar,
			int cant_cause, boolean cons_cause, QuestionType typeCause,
			int cant_rec, boolean cons_rec, QuestionType typeRec) {
		super();
		this.id_config = id_config;
		this.process = process;
		this.cant_total = cant_total;
		this.cant_var = cant_var;
		this.cons_var = cons_var;
		this.typeVar = typeVar;
		this.cant_cause = cant_cause;
		this.cons_cause = cons_cause;
		this.typeCause = typeCause;
		this.cant_rec = cant_rec;
		this.cons_rec = cons_rec;
		this.typeRec = typeRec;
	}
	
	public ProcessConfig(int process, int cant_total,
			int cant_var, boolean cons_var, QuestionType typeVar,
			int cant_cause, boolean cons_cause, QuestionType typeCause,
			int cant_rec, boolean cons_rec, QuestionType typeRec) {
		super();
		this.process = process;
		this.cant_total = cant_total;
		this.cant_var = cant_var;
		this.cons_var = cons_var;
		this.typeVar = typeVar;
		this.cant_cause = cant_cause;
		this.cons_cause = cons_cause;
		this.typeCause = typeCause;
		this.cant_rec = cant_rec;
		this.cons_rec = cons_rec;
		this.typeRec = typeRec;
	}

	public int getId_config() {
		return id_config;
	}

	public void setId_config(int id_config) {
		this.id_config = id_config;
	}

	public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		this.process = process;
	}

	public int getCant_total() {
		return cant_total;
	}

	public void setCant_total(int cant_total) {
		this.cant_total = cant_total;
	}

	public int getCant_var() {
		return cant_var;
	}

	public void setCant_var(int cant_var) {
		this.cant_var = cant_var;
	}

	public boolean isCons_var() {
		return cons_var;
	}

	public void setCons_var(boolean cons_var) {
		this.cons_var = cons_var;
	}

	public QuestionType getTypeVar() {
		return typeVar;
	}

	public void setTypeVar(QuestionType typeVar) {
		this.typeVar = typeVar;
	}

	public int getCant_cause() {
		return cant_cause;
	}

	public void setCant_cause(int cant_cause) {
		this.cant_cause = cant_cause;
	}

	public boolean isCons_cause() {
		return cons_cause;
	}

	public void setCons_cause(boolean cons_cause) {
		this.cons_cause = cons_cause;
	}

	public QuestionType getTypeCause() {
		return typeCause;
	}

	public void setTypeCause(QuestionType typeCause) {
		this.typeCause = typeCause;
	}

	public int getCant_rec() {
		return cant_rec;
	}

	public void setCant_rec(int cant_rec) {
		this.cant_rec = cant_rec;
	}

	public boolean isCons_rec() {
		return cons_rec;
	}

	public void setCons_rec(boolean cons_rec) {
		this.cons_rec = cons_rec;
	}

	public QuestionType getTypeRec() {
		return typeRec;
	}

	public void setTypeRec(QuestionType typeRec) {
		this.typeRec = typeRec;
	}
}
