package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import contentClass.Variable;
import utils.VariableType;

public class UtilsServices {

	//introducir nueva area en la basse de datos
	public static String createVariable(String nameVar, VariableType type, int max_value, int min_value, int process) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO variable VALUES (DEFAULT,?,?,?,?,?)";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, nameVar);
			cs.setString(2, type.toString());
			cs.setInt(3, max_value);
			cs.setInt(4, min_value);
			cs.setInt(5, process);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	//encuentra variable
	public static int findVar(String name, int id_process) throws SQLException {
		int id_var = 0;
		try{
			String sqlSentenc = "SELECT * FROM variable WHERE name_variable = ? and process = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name);
			cs.setInt(2, id_process);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				id_var = rs.getInt("id_variable");
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return id_var;
	}
	
	public static ArrayList<Variable> getVar(int id_process) throws SQLException {
		ArrayList<Variable> variables = new ArrayList<>();
		try{
			String sqlSentenc = "SELECT * FROM variable WHERE process = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id_process);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				Variable v = new Variable(rs.getInt("id_variable"),rs.getString("name_variable"),rs.getString("type"),
						rs.getInt("max_value"),rs.getInt("min_value"));
				variables.add(v);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return variables;
	}

	//encuentra causa
	public static int findCause(String name, int id_process) throws SQLException {
		int id_var = 0;
		try{
			String sqlSentenc = "SELECT * FROM cause WHERE description = ? and process = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name);
			cs.setInt(2, id_process);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				id_var = rs.getInt("id_cause");
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return id_var;
	}


	//introducir nueva causa en la basse de datos
	public static String createCause(String descr, int process) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO cause VALUES (DEFAULT,?,?)";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, descr);
			cs.setInt(2, process);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	//introducir nueva recomendacion en la basse de datos
	public static String createRec(String descr, int process) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO recomendation VALUES (DEFAULT,?,?)";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, descr);
			cs.setInt(2, process);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	//introducir regla var-causa
	public static String createRuleVC(int variable, int cause, String status) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO variable_cause VALUES (DEFAULT,?,?,?)";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, variable);
			cs.setInt(2, cause);
			cs.setString(3, status);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static ArrayList<String> findState(int id_var) throws SQLException {
		ArrayList<String> variables = new ArrayList<>();
		try{
			String sqlSentenc = "SELECT * FROM variable_cause WHERE variable = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id_var);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				String v = rs.getString("status");
				variables.add(v);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return variables;
	}
}
