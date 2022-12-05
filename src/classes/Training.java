package classes;

public class Training {
	int train_id;
    int process_id;
    int operator;
    int cant_try;
    double var_note;
    double cause_note;
    double rec_note;
    double general_note;
    int cant_aprov;
    
	public Training(int train_id, int process_id, int operator, int cant_try,
			double var_note, double cause_note, double rec_note,
			double general_note, int cant_aprov) {
		super();
		this.train_id = train_id;
		this.process_id = process_id;
		this.operator = operator;
		this.cant_try = cant_try;
		this.var_note = var_note;
		this.cause_note = cause_note;
		this.rec_note = rec_note;
		this.general_note = general_note;
		this.cant_aprov = cant_aprov;
	}
	
	public int getTrain_id() {
		return train_id;
	}
	public void setTrain_id(int train_id) {
		this.train_id = train_id;
	}
	public int getCant_aprov() {
		return cant_aprov;
	}
	public void setCant_aprov(int cant_aprov) {
		this.cant_aprov = cant_aprov;
	}	
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	public int getCant_try() {
		return cant_try;
	}
	public void setCant_try(int cant_try) {
		this.cant_try = cant_try;
	}
	public double getVar_note() {
		return var_note;
	}
	public void setVar_note(double var_note) {
		this.var_note = var_note;
	}
	public double getCause_note() {
		return cause_note;
	}
	public void setCause_note(double cause_note) {
		this.cause_note = cause_note;
	}
	public double getRec_note() {
		return rec_note;
	}
	public void setRec_note(double rec_note) {
		this.rec_note = rec_note;
	}
	public double getGeneral_note() {
		return general_note;
	}
	public void setGeneral_note(double general_note) {
		this.general_note = general_note;
	}
}
