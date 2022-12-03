package classes;

public class Variable {
	int var_id;
	int process_id;
	String var_name;
	String var_type;
	double min_value;
	double max_value;
	
	public Variable(int process_id, String var_name,
			String var_type, double min_value, double max_value) {
		super();
		this.process_id = process_id;
		this.var_name = var_name;
		this.var_type = var_type;
		this.min_value = min_value;
		this.max_value = max_value;
	}
	
	public Variable(int var_id, int process_id, String var_name,
			String var_type, double min_value, double max_value) {
		super();
		this.var_id = var_id;
		this.process_id = process_id;
		this.var_name = var_name;
		this.var_type = var_type;
		this.min_value = min_value;
		this.max_value = max_value;
	}
	
	public int getVar_id() {
		return var_id;
	}
	public void setVar_id(int var_id) {
		this.var_id = var_id;
	}
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}
	public String getVar_name() {
		return var_name;
	}
	public void setVar_name(String var_name) {
		this.var_name = var_name;
	}
	public String getVar_type() {
		return var_type;
	}
	public void setVar_type(String var_type) {
		this.var_type = var_type;
	}
	public double getMin_value() {
		return min_value;
	}
	public void setMin_value(double min_value) {
		this.min_value = min_value;
	}
	public double getMax_value() {
		return max_value;
	}
	public void setMax_value(double max_value) {
		this.max_value = max_value;
	}
}
