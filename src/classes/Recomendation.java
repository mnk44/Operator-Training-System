package classes;

public class Recomendation {
	int rec_id;
	int process_id;
	String rec_name;
	
	public Recomendation(int rec_id, int process_id, String rec_name) {
		super();
		this.rec_id = rec_id;
		this.process_id = process_id;
		this.rec_name = rec_name;
	}
	
	public int getRec_id() {
		return rec_id;
	}
	public void setRec_id(int rec_id) {
		this.rec_id = rec_id;
	}
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}
	public String getRec_name() {
		return rec_name;
	}
	public void setRec_name(String rec_name) {
		this.rec_name = rec_name;
	}
}
