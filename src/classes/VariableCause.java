package classes;

import enums.VariableState;

public class VariableCause {
	int table_id;
	int variable_id;
	VariableState state;
	int cause_id;
	
	public VariableCause(int variable_id, String state, int cause_id) {
		super();
		this.variable_id = variable_id;
		this.state = VariableState.valueOf(state);
		this.cause_id = cause_id;
	}

	public VariableCause(int table_id, int variable_id, String state,
			int cause_id) {
		super();
		this.table_id = table_id;
		this.variable_id = variable_id;
		this.state = VariableState.valueOf(state);
		this.cause_id = cause_id;
	}
	
	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	public int getVariable_id() {
		return variable_id;
	}
	public void setVariable_id(int variable_id) {
		this.variable_id = variable_id;
	}
	public VariableState getState() {
		return state;
	}
	public void setState(VariableState state) {
		this.state = state;
	}
	public int getCause_id() {
		return cause_id;
	}
	public void setCause_id(int cause_id) {
		this.cause_id = cause_id;
	}
}
