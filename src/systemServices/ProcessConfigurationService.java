package systemServices;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import systemClass.ProcessConfiguration;

public class ProcessConfigurationService {
	
	public static String newConfiguration(ProcessConfiguration config) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO process_configuration VALUES (DEFAULT,?,?,?,?,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, config.getProcess_id());
			cs.setInt(2, config.getCant_try());
			cs.setInt(3, config.getTry_aprove());
			cs.setBoolean(4, config.isConsecutive());
			cs.setString(5, config.getVar_question().toString());
			cs.setString(6, config.getCause_question().toString());
			cs.setString(7, config.getRec_question().toString());
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
	
	public static String newProcessUser(int user_id, int process_id) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO user_process VALUES (DEFAULT,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, user_id);
			cs.setInt(2, process_id);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
}
