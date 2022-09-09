package systemServices;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import systemClass.Variable;

public class VariableService {
	
	public static String newVariable(Variable variable) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO variable VALUES (DEFAULT,?,?,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, variable.getVariable_name());
			cs.setString(2, variable.getVariable_type().toString());
			cs.setInt(3, variable.getMin_value());
			cs.setInt(4, variable.getMax_value());
			cs.setInt(5, variable.getVariable_process());
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	public static String updateVariable(Variable variable) throws SQLException{
		try{
			String sqlSentenc = "UPDATE variable SET variable_name = ?, variable_type = ?"
					+ "min_value = ?, max_value = ?, variable_process = ? WHERE variable_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, variable.getVariable_name());
			cs.setString(2, variable.getVariable_type().toString());
			cs.setInt(3, variable.getMin_value());
			cs.setInt(4, variable.getMax_value());
			cs.setInt(5, variable.getVariable_process());
			cs.setInt(6, variable.getVariable_id());
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	public static String deleteVariable(int variable_id) throws SQLException{
		try{
			String sqlSentenc = "DELETE FROM variable WHERE process_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, variable_id);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	public static Object findId(int variable_id) throws SQLException {
		Variable variable = null;
		try{
			String sqlSentenc = "SELECT * FROM variable WHERE variable_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, variable_id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				variable = new Variable(rs.getInt("variable_id"),rs.getString("variable_name"), rs.getString("variable_type"),
						rs.getInt("min_value"), rs.getInt("max_value"), rs.getInt("variable_process"));
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return variable;
	}
	
	public static Object findName(int variable_id, int variable_process) throws SQLException {
		Variable variable = null;
		try{
			String sqlSentenc = "SELECT * FROM variable WHERE variable_id = ? and variable_process = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, variable_id);
			cs.setInt(2, variable_process);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				variable = new Variable(rs.getInt("variable_id"),rs.getString("variable_name"), rs.getString("variable_type"),
						rs.getInt("min_value"), rs.getInt("max_value"), rs.getInt("variable_process"));
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return variable;
	}

	public static Object getVariables(int process_id) throws SQLException{
		ArrayList<Variable> variableList = new ArrayList<Variable>();
		try{
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM variable WHERE variable_process = ?");
			while(rs.next()){
				Variable variable = new Variable(rs.getInt("variable_id"),rs.getString("variable_name"), rs.getString("variable_type"),
						rs.getInt("min_value"), rs.getInt("max_value"), rs.getInt("variable_process"));
				variableList.add(variable);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return variableList;
	}
}
