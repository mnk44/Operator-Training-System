package systemServices;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import systemClass.Cause;

public class CauseService {
	
	public static String newCause(Cause cause) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO cause VALUES (DEFAULT,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, cause.getCause_name());
			cs.setInt(2, cause.getCause_process());
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	public static Object findId(int cause_id) throws SQLException {
		Cause cause = null;
		try{
			String sqlSentenc = "SELECT * FROM cause WHERE cause_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, cause_id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				cause = new Cause(rs.getInt("cause_id"),rs.getString("cause_name"), rs.getInt("cause_process"));
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return cause;
	}
	
	public static Object findName(String cause_name, int cause_process) throws SQLException {
		Cause cause = null;
		try{
			String sqlSentenc = "SELECT * FROM cause WHERE cause_name = ? and cause_process = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, cause_name);
			cs.setInt(2, cause_process);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				cause = new Cause(rs.getInt("cause_id"),rs.getString("cause_name"), rs.getInt("cause_process"));
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return cause;
	}

	public static Object getCauses(int process_id) throws SQLException{
		ArrayList<Cause> causeList = new ArrayList<Cause>();
		try{
			String sqlSentenc = "SELECT * FROM cause WHERE cause_process = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, process_id);
			ResultSet rs = cs.executeQuery();
			while(rs.next()){
				Cause cause = new Cause(rs.getInt("cause_id"),rs.getString("cause_name"), rs.getInt("cause_process"));
				causeList.add(cause);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return causeList;
	}
}
