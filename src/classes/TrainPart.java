package classes;

public class TrainPart {
	int table_id;
    int training_id;
    double time_rest;
    int cant_aproved;
    String test_type;
    double final_note;
    
	public TrainPart(int table_id, int training_id, double time_rest,
			int cant_aproved, String test_type, double final_note) {
		super();
		this.table_id = table_id;
		this.training_id = training_id;
		this.time_rest = time_rest;
		this.cant_aproved = cant_aproved;
		this.test_type = test_type;
		this.final_note = final_note;
	}
	
	public TrainPart(int training_id, double time_rest,
			int cant_aproved, String test_type, double final_note) {
		super();
		this.training_id = training_id;
		this.time_rest = time_rest;
		this.cant_aproved = cant_aproved;
		this.test_type = test_type;
		this.final_note = final_note;
	}
	
	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	public int getTraining_id() {
		return training_id;
	}
	public void setTraining_id(int training_id) {
		this.training_id = training_id;
	}
	public double getTime_rest() {
		return time_rest;
	}
	public void setTime_rest(double time_rest) {
		this.time_rest = time_rest;
	}
	public int getCant_aproved() {
		return cant_aproved;
	}
	public void setCant_aproved(int cant_aproved) {
		this.cant_aproved = cant_aproved;
	}
	public String getTest_type() {
		return test_type;
	}
	public void setTest_type(String test_type) {
		this.test_type = test_type;
	}
	public double getFinal_note() {
		return final_note;
	}
	public void setFinal_note(double final_note) {
		this.final_note = final_note;
	}
}
