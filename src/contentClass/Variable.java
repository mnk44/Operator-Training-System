package contentClass;

import utils.VariableStatus;
import utils.VariableType;
import cu.edu.cujae.ceis.tree.general.GeneralTree;

public class Variable {
	
    private String variable_name;
    private VariableType type;
    protected VariableStatus status;
    protected GeneralTree<String> tree;
    
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
	public GeneralTree<String> getTree() {
		return tree;
	}
	public void setTree(GeneralTree<String> tree) {
		this.tree = tree;
	}   
}
