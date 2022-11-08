package classes;

public class Cause {
	int cause_id;
	String cause_name;
	int cause_process;
	
	public Cause(String cause_name, int cause_process) {
		super();
		this.cause_name = cause_name;
		this.cause_process = cause_process;
	}
	
	public Cause(int cause_id, String cause_name, int cause_process) {
		super();
		this.cause_id = cause_id;
		this.cause_name = cause_name;
		this.cause_process = cause_process;
	}
	
	public int getCause_id() {
		return cause_id;
	}
	public void setCause_id(int cause_id) {
		this.cause_id = cause_id;
	}
	public String getCause_name() {
		return cause_name;
	}
	public void setCause_name(String cause_name) {
		this.cause_name = cause_name;
	}
	public int getCause_process() {
		return cause_process;
	}
	public void setCause_process(int cause_process) {
		this.cause_process = cause_process;
	}
}
