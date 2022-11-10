package classes;

public class Cause {
	int cause_id;
	int process_id;
	String cause_name;
	
	public Cause(int cause_id, int process_id, String cause_name) {
		super();
		this.cause_id = cause_id;
		this.process_id = process_id;
		this.cause_name = cause_name;
	}
	
	public int getCause_id() {
		return cause_id;
	}
	public void setCause_id(int cause_id) {
		this.cause_id = cause_id;
	}
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}
	public String getCause_name() {
		return cause_name;
	}
	public void setCause_name(String cause_name) {
		this.cause_name = cause_name;
	}
}
