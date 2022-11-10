package classes;

public class CauseRecomendation {
	int table_id;
	int cause_id;
	int rec_id;
	int process_id;
	
	public CauseRecomendation(int table_id, int cause_id, int rec_id,
			int process_id) {
		super();
		this.table_id = table_id;
		this.cause_id = cause_id;
		this.rec_id = rec_id;
		this.process_id = process_id;
	}
	
	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	public int getCause_id() {
		return cause_id;
	}
	public void setCause_id(int cause_id) {
		this.cause_id = cause_id;
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
}
