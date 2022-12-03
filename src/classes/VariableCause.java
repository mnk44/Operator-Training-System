package classes;

public class VariableCause {
	int table_id;
	int proces_id;
	int var_id;
	String state_var;
	int cause_id;
	
	public VariableCause(int proces_id, int var_id,
			String state_var, int cause_id) {
		super();
		this.proces_id = proces_id;
		this.var_id = var_id;
		this.state_var = state_var;
		this.cause_id = cause_id;
	}
	
	public VariableCause(int table_id, int proces_id, int var_id,
			String state_var, int cause_id) {
		super();
		this.table_id = table_id;
		this.proces_id = proces_id;
		this.var_id = var_id;
		this.state_var = state_var;
		this.cause_id = cause_id;
	}

	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	public int getProces_id() {
		return proces_id;
	}
	public void setProces_id(int proces_id) {
		this.proces_id = proces_id;
	}
	public int getVar_id() {
		return var_id;
	}
	public void setVar_id(int var_id) {
		this.var_id = var_id;
	}
	public String getState_var() {
		return state_var;
	}
	public void setState_var(String state_var) {
		this.state_var = state_var;
	}
	public int getCause_id() {
		return cause_id;
	}
	public void setCause_id(int cause_id) {
		this.cause_id = cause_id;
	}
}
