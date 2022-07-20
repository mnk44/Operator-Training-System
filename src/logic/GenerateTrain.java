package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import contentClass.Variable;
import services.UtilsServices;
import utils.VariableType;

public class GenerateTrain {

	//los primeros 10 elementos son para mostrar, los otros 5 son las respuestas bien
	public static ArrayList<String> getVariables(int id_process) throws SQLException{
		ArrayList<Variable> variables = UtilsServices.getVar(id_process);
		ArrayList<String> var = new ArrayList<>();
		
		ArrayList<String> bien = new ArrayList<>();
		
		if(variables.size() > 10){
			while(var.size() <= 10){
				int position = (int) (Math.random()*6 + (variables.size() - 6));
				String sentencia = variables.get(position).getVariable_name(); //aqui guardo el nombre
				if(position%2==0){
					ArrayList<String> s = UtilsServices.findState(variables.get(position).getId());
					for(int i=0; i<s.size(); i++){
						bien.add(sentencia + " con estado " + s.get(i));
						var.add(sentencia + " con estado " + s.get(i));
					}
				}else{
					if(Math.random()*6%2 == 0){
						if(variables.get(position).getType() != VariableType.DISCRETA){
							var.add(sentencia + " con estado NORMAL");
						}else{
							var.add(sentencia + " con estado POSITIVO");
						}
					}
				}
			}
			
			var.add("&");
			
			for(int i=0; i<bien.size(); i++){
				var.add(bien.get(i));
			}
		}
		return var;
	}
}
