package systemServices;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import systemClass.CauseRecomendation;
import systemClass.VariableCause;

public class RulesService {
	
	public static String newVariableCause(VariableCause rule) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO variableCause VALUES (DEFAULT,?,?,?)";
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
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM variableCause");
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
			String sqlSentenc = "INSERT INTO causeRecomendation VALUES (DEFAULT,?,?)";
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
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM causeRecomendation");
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
}
