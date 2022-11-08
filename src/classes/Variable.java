package classes;

import enums.VariableTypes;

public class Variable {
	int variable_id;
	String variable_name;
	VariableTypes variable_type;
	int min_value;
	int max_value;
	int variable_process;
	
	public Variable(String variable_name, String variable_type,
			int variable_process) {
		super();
		this.variable_name = variable_name;
		this.variable_type = VariableTypes.valueOf(variable_type);
		this.min_value = -1;
		this.max_value = -1;
		this.variable_process = variable_process;
	}

	public Variable(String variable_name, String variable_type,
			int min_value, int max_value, int variable_process) {
		super();
		this.variable_name = variable_name;
		this.variable_type = VariableTypes.valueOf(variable_type);
		this.min_value = min_value;
		this.max_value = max_value;
		this.variable_process = variable_process;
	}

	public Variable(int variable_id, String variable_name,
			String variable_type, int variable_process) {
		super();
		this.variable_id = variable_id;
		this.variable_name = variable_name;
		this.variable_type = VariableTypes.valueOf(variable_type);
		this.min_value = -1;
		this.max_value = -1;
		this.variable_process = variable_process;
	}

	public Variable(int variable_id, String variable_name,
			String variable_type, int min_value, int max_value,
			int variable_process) {
		super();
		this.variable_id = variable_id;
		this.variable_name = variable_name;
		this.variable_type = VariableTypes.valueOf(variable_type);
		this.min_value = min_value;
		this.max_value = max_value;
		this.variable_process = variable_process;
	}
	
	public int getVariable_id() {
		return variable_id;
	}
	public void setVariable_id(int variable_id) {
		this.variable_id = variable_id;
	}
	public String getVariable_name() {
		return variable_name;
	}
	public void setVariable_name(String variable_name) {
		this.variable_name = variable_name;
	}
	public VariableTypes getVariable_type() {
		return variable_type;
	}
	public void setVariable_type(VariableTypes variable_type) {
		this.variable_type = variable_type;
	}
	public int getMin_value() {
		return min_value;
	}
	public void setMin_value(int min_value) {
		this.min_value = min_value;
	}
	public int getMax_value() {
		return max_value;
	}
	public void setMax_value(int max_value) {
		this.max_value = max_value;
	}
	public int getVariable_process() {
		return variable_process;
	}
	public void setVariable_process(int variable_process) {
		this.variable_process = variable_process;
	}
}
