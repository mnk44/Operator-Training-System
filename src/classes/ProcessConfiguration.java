package classes;

public class ProcessConfiguration {
	int table_id;
	int process_id;
	int time_limit;
	int cant_questions;
	int cant_aprov;
	String type_var;
	String type_cause;
	String type_rec;
	
	public ProcessConfiguration(int process_id, int time_limit,
			int cant_questions, int cant_aprov, String type_var,
			String type_cause, String type_rec) {
		super();
		this.process_id = process_id;
		this.time_limit = time_limit;
		this.cant_questions = cant_questions;
		this.cant_aprov = cant_aprov;
		this.type_var = type_var;
		this.type_cause = type_cause;
		this.type_rec = type_rec;
	}
	
	public ProcessConfiguration(int table_id, int process_id, int time_limit,
			int cant_questions, int cant_aprov, String type_var,
			String type_cause, String type_rec) {
		super();
		this.table_id = table_id;
		this.process_id = process_id;
		this.time_limit = time_limit;
		this.cant_questions = cant_questions;
		this.cant_aprov = cant_aprov;
		this.type_var = type_var;
		this.type_cause = type_cause;
		this.type_rec = type_rec;
	}
	
	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}
	public int getTime_limit() {
		return time_limit;
	}
	public void setTime_limit(int time_limit) {
		this.time_limit = time_limit;
	}
	public int getCant_questions() {
		return cant_questions;
	}
	public void setCant_questions(int cant_questions) {
		this.cant_questions = cant_questions;
	}
	public int getCant_aprov() {
		return cant_aprov;
	}
	public void setCant_aprov(int cant_aprov) {
		this.cant_aprov = cant_aprov;
	}
	public String getType_var() {
		return type_var;
	}
	public void setType_var(String type_var) {
		this.type_var = type_var;
	}
	public String getType_cause() {
		return type_cause;
	}
	public void setType_cause(String type_cause) {
		this.type_cause = type_cause;
	}
	public String getType_rec() {
		return type_rec;
	}
	public void setType_rec(String type_rec) {
		this.type_rec = type_rec;
	}
}
