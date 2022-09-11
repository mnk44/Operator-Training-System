package systemClass;

public class CauseRecomendation {
	int table_id;
	int cause_id;
	int recomendation_id;
	
	public CauseRecomendation(int cause_id, int recomendation_id) {
		super();
		this.cause_id = cause_id;
		this.recomendation_id = recomendation_id;
	}

	public CauseRecomendation(int table_id, int cause_id, int recomendation_id) {
		super();
		this.table_id = table_id;
		this.cause_id = cause_id;
		this.recomendation_id = recomendation_id;
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
	public int getRecomendation_id() {
		return recomendation_id;
	}
	public void setRecomendation_id(int recomendation_id) {
		this.recomendation_id = recomendation_id;
	}
}
