package contentClass;

import utils.VariableStatus;
import utils.VariableType;

public class Variable {
	private int id;
    private String variable_name;
    private VariableType type;
    private int max;
    private int min;
    
	public Variable(String variable_name,
			VariableType type) {
		super();
		this.variable_name = variable_name;
		this.type = type;
	}

	public Variable() {
		super();
	}
	
	public Variable(int int1, String string, String string2, int int2, int int3) {
		// TODO Auto-generated constructor stub
		this.id = int1;
		this.variable_name = string;
		this.type = VariableType.valueOf(string2);
		this.max = int2;
		this.min = int3;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

}
