package contentClass;

import utils.VariableStatus;
import utils.VariableType;

public class Variable {
	
    private String variable_name;
    private VariableType type;
    protected VariableStatus status;
    
	public Variable(String variable_name,
			VariableType type) {
		super();
		this.variable_name = variable_name;
		this.type = type;
	}

	public Variable() {
		super();
	}
	
	public String getVariable_name() {
		return variable_name;
	}
	public void setVariable_name(String variable_name) {
		this.variable_name = variable_name;
	}
	public VariableType getType() {
		return type;
	}
	public void setType(VariableType type) {
		this.type = type;
	}
	public VariableStatus getStatus() {
		return status;
	}
	public void setStatus(VariableStatus status) {
		this.status = status;
	} 
}
