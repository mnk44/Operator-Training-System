package extras;

import java.sql.SQLException;
import java.util.ArrayList;

import services.ProcessService;
import services.TrainingService;
import classes.ProcessConfiguration;
import classes.Process;
import classes.Training;
import classes.TrainingPrepare;
import classes.User;

public class PrepareProcess {

	public static TrainingPrepare prepareTraining(User operator, ArrayList<Process> process, ArrayList<ProcessConfiguration> configurations) throws SQLException{
		ArrayList<Integer> userProcess = ProcessService.getProcessOperator(operator);
		ArrayList<ProcessConfiguration> userConfig = new ArrayList<>();
		ArrayList<String> processName = new ArrayList<>();
		ArrayList<Training> train = TrainingService.searchOperator(operator.getUser_id());
		
		for(int i=0; i<process.size(); i++){
			ProcessConfiguration conf = findConfig(configurations, process.get(i).getProcess_id());
			if(exist(userProcess, process.get(i)) || conf.isFor_every()){
				userConfig.add(conf);
				processName.add(process.get(i).getProcess_name());
			}
		}
		
		TrainingPrepare trainning = new TrainingPrepare(processName, userConfig, train);
		return trainning;
	}
	
	public static boolean exist(ArrayList<Integer> userProcess, Process process){
		boolean exist = false;
		int cont = 0;
		
		while(cont < userProcess.size() && !exist){
			if(userProcess.get(cont) == process.getProcess_id()){
				exist = true;
			}
			cont = cont + 1;
		}
		
		return exist;
	}
	
	public static ProcessConfiguration findConfig(ArrayList<ProcessConfiguration> configurations, int process){
		ProcessConfiguration conf = null;
		int cont = 0;
		
		while(cont < configurations.size() && conf == null){
			if(configurations.get(cont).getProcess_id() == process){
				conf = configurations.get(cont);
			}
			cont = cont + 1;
		}
		
		return conf;
	}
}
