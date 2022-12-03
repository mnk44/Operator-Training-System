package classes;

import java.util.ArrayList;

public class TrainingPrepare {
	ArrayList<String> process;
	ArrayList<ProcessConfiguration> configurationList;
	ArrayList<Training> training;
	
	public ArrayList<Training> getTraining() {
		return training;
	}
	public void setTraining(ArrayList<Training> training) {
		this.training = training;
	}
	public ArrayList<String> getProcess() {
		return process;
	}
	public void setProcess(ArrayList<String> process) {
		this.process = process;
	}
	public ArrayList<ProcessConfiguration> getConfigurationList() {
		return configurationList;
	}
	public void setConfigurationList(
			ArrayList<ProcessConfiguration> configurationList) {
		this.configurationList = configurationList;
	}
		
	public TrainingPrepare(ArrayList<String> process,
			ArrayList<ProcessConfiguration> configurationList,
			ArrayList<Training> training) {
		super();
		this.process = process;
		this.configurationList = configurationList;
		this.training = training;
	}
}
