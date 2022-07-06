package services;

import java.sql.CallableStatement;
import java.sql.SQLException;

import contentClass.ProcessConfig;

public class ProcessConfigService {

	//introducir nuevo proceso en la basse de datos
	public static String createProcessConfig(ProcessConfig process) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO process_configuration VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?,?)";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, process.getCant_var());
			cs.setBoolean(2, process.isCons_var());
			cs.setInt(3, process.getCant_cause());
			cs.setBoolean(4, process.isCons_cause());
			cs.setInt(5, process.getCant_rec());
			cs.setBoolean(6, process.isCons_rec());
			cs.setInt(7, process.getProcess());
			cs.setString(8, process.getTypeVar().toString());
			cs.setString(9, process.getTypeCause().toString());
			cs.setString(10, process.getTypeRec().toString());
			cs.setInt(11, process.getCant_total());
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static String createUserRelate(int process, int user) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO user_process VALUES (DEFAULT,?,?)";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, process);
			cs.setInt(1, user);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static String deleteAllRelations(int id_process) throws SQLException{
		try{
			String sqlSentenc = "DELETE FROM user_process WHERE process = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id_process);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
}
