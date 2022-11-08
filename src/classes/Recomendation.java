package classes;

public class Recomendation {
	int recomendation_id;
	String recomendation_name;
	int recomendation_process;
	
	public Recomendation(String recomendation_name, int recomendation_process) {
		super();
		this.recomendation_name = recomendation_name;
		this.recomendation_process = recomendation_process;
	}
	
	public Recomendation(int recomendation_id, String recomendation_name, int recomendation_process) {
		super();
		this.recomendation_id = recomendation_id;
		this.recomendation_name = recomendation_name;
		this.recomendation_process = recomendation_process;
	}

	public int getRecomendation_id() {
		return recomendation_id;
	}

	public void setRecomendation_id(int recomendation_id) {
		this.recomendation_id = recomendation_id;
	}

	public String getRecomendation_name() {
		return recomendation_name;
	}

	public void setRecomendation_name(String recomendation_name) {
		this.recomendation_name = recomendation_name;
	}

	public int getRecomendation_process() {
		return recomendation_process;
	}

	public void setRecomendation_process(int recomendation_process) {
		this.recomendation_process = recomendation_process;
	}
}
