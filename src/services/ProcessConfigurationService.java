package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import assistants.MultiTableList;
import classes.ProcessConfiguration;
import classes.User;
import enums.AccionTrace;
import enums.SystemClass;

public class ProcessConfigurationService {
	
	public static String newConfiguration(ProcessConfiguration config, int trace_user, AccionTrace trace_accion,
			SystemClass trace_class, String trace_class_id, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO process_configuration VALUES (DEFAULT,?,?,?,?,?,?,?)"
					+ "INSERT INTO system_trace VALUES (DEFAULT,?,?,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, config.getProcess_id());
			cs.setInt(2, config.getCant_try());
			cs.setInt(3, config.getTry_aprove());
			cs.setBoolean(4, config.isConsecutive());
			cs.setString(5, config.getVar_question().toString());
			cs.setString(6, config.getCause_question().toString());
			cs.setString(7, config.getRec_question().toString());
			cs.setInt(8, trace_user);
			cs.setString(9, trace_accion.toString());
			cs.setString(10, trace_class.toString());
			cs.setString(11, trace_class_id);
			cs.setTimestamp(12, trace_date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static String updateConfiguration(ProcessConfiguration config) throws SQLException{
		try{
			String sqlSentenc = "UPDATE process_configuration SET cant_try = ?, try_aprove = ?, consecutive = ?,"
					+ "var_question = ?, cause_question = ?, rec_question = ? WHERE table_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, config.getCant_try());
			cs.setInt(2, config.getTry_aprove());
			cs.setBoolean(3, config.isConsecutive());
			cs.setString(4, config.getVar_question().toString());
			cs.setString(5, config.getCause_question().toString());
			cs.setString(6, config.getRec_question().toString());
			cs.setInt(7, config.getClass_id());
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static Object findProcess(int process_id) throws SQLException {
		ProcessConfiguration process = null;
		try{
			String sqlSentenc = "SELECT * FROM process_configuration WHERE process_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, process_id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				process = new ProcessConfiguration(rs.getInt("table_id"),rs.getInt("process_id"),rs.getInt("cant_try"),
						rs.getInt("try_aprove"),rs.getBoolean("consecutive"),rs.getString("var_question"),rs.getString("cause_question"),
						rs.getString("rec_question"));
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return process;
	}
	
	public static String newProcessUser(ArrayList<User> users, int process_id) throws SQLException{
		try{
			int cont = 0;
			String sqlSentenc = "INSERT INTO user_process VALUES "
					+ MultiTableList.fillUser(users);
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			for (int i = 0; i < users.size(); i++) {
				cs.setInt(cont++, users.get(i).getUser_id());
				cs.setInt(cont++, process_id);
			}
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
}
