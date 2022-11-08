package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import assistants.MultiTableList;
import classes.Cause;
import classes.CauseRecomendation;
import classes.Recomendation;
import classes.Variable;
import classes.VariableCause;

public class RulesService {
	
	public static String newVariableCause(VariableCause rule) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO variable_cause VALUES (DEFAULT,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, rule.getVariable_id());
			cs.setString(2, rule.getState().toString());
			cs.setInt(3, rule.getCause_id());
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static Object getVariableCause() throws SQLException{
		ArrayList<VariableCause> ruleList = new ArrayList<VariableCause>();
		try{
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM variable_cause");
			while(rs.next()){
				VariableCause rule = new VariableCause(rs.getInt("table_id"),rs.getInt("variable_id"),
						rs.getString("state"),rs.getInt("cause_id"));
				ruleList.add(rule);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return ruleList;
	}
	
	public static String newCauseRecomendation(CauseRecomendation rule) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO cause_recomendation VALUES (DEFAULT,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, rule.getCause_id());
			cs.setInt(2, rule.getRecomendation_id());
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static Object getCauseRecomendation() throws SQLException{
		ArrayList<CauseRecomendation> ruleList = new ArrayList<CauseRecomendation>();
		try{
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM cause_recomendation");
			while(rs.next()){
				CauseRecomendation rule = new CauseRecomendation(rs.getInt("table_id"),rs.getInt("cause_id"),
						rs.getInt("recomendation_id"));
				ruleList.add(rule);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return ruleList;
	}
	
	public static String insertAnmInfo(ArrayList<Variable> vars, ArrayList<Cause> causes, ArrayList<Recomendation> recs){
		int cont = 0;
		try{
			String sqlSentenc = "INSERT INTO variable VALUES " + MultiTableList.fillVariable(vars) +
					"INSERT INTO cause VALUES " + MultiTableList.fillCause(causes) +
					"INSERT INTO recomendation VALUES " + MultiTableList.fillRecomendations(recs);
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			for (int i = 0; i < vars.size(); i++) {
				cs.setString(cont++, vars.get(i).getVariable_name());
				cs.setString(cont++, vars.get(i).getVariable_type().toString());
				cs.setInt(cont++, vars.get(i).getMin_value());
				cs.setInt(cont++, vars.get(i).getMax_value());
				cs.setInt(cont++, vars.get(i).getVariable_process());
			}
			for (int j = 0; j < causes.size(); j++) {
				cs.setString(cont++, causes.get(j).getCause_name());
				cs.setInt(cont++, causes.get(j).getCause_process());
			}
			for (int k = 0; k < recs.size(); k++) {
				cs.setString(cont++, recs.get(k).getRecomendation_name());
				cs.setInt(cont++, recs.get(k).getRecomendation_process());
			}
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
}
