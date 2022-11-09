package extras;

import java.util.ArrayList;

import classes.Cause;
import classes.Recomendation;
import classes.User;
import classes.Variable;

public class MultiTableList {
	
	public static String fillUser(ArrayList<User> users){
		String result = null;
		String aux = "(DEFAULT,?,?)";
		
		for (int i = 0; i < users.size(); i++) {
			result = result + ", " + aux;
		}
		
		return result + ";";
	}
	
	public static String fillVariable(ArrayList<Variable> vars){
		String result = null;
		String aux = "(DEFAULT,?,?,?,?,?)";
		
		for (int i = 0; i < vars.size(); i++) {
			result = result + ", " + aux;
		}
		
		return result + ";";
	}
	
	public static String fillCause(ArrayList<Cause> causes){
		String result = null;
		String aux = "(DEFAULT,?,?)";
		
		for (int i = 0; i < causes.size(); i++) {
			result = result + ", " + aux;
		}
		
		return result + ";";
	}
	
	public static String fillRecomendations(ArrayList<Recomendation> recs){
		String result = null;
		String aux = "(DEFAULT,?,?)";
		
		for (int i = 0; i < recs.size(); i++) {
			result = result + ", " + aux;
		}
		
		return result + ";";
	}
}
