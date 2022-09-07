package systemServices;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import systemClass.FactoryProcess;

public class FactoryProcessService {
	
	public static String newProcess(FactoryProcess process) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO process VALUES (DEFAULT,?,?,?,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, process.getProcess_name());
			cs.setInt(2, process.getProcess_area());
			cs.setTimestamp(3, process.getCreation_date());
			cs.setBytes(4, process.getProcess_img());
			cs.setBytes(5, process.getProcess_anm());
			cs.setBytes(6, process.getProcess_drl());
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	public static String updateProcess(FactoryProcess process) throws SQLException{
		try{
			String sqlSentenc = "UPDATE process SET process_name = ?, process_area = ?, creation_date = ?"
					+ "process_img = ?, process_anm = ?, process_drl = ? WHERE process_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, process.getProcess_name());
			cs.setInt(2, process.getProcess_area());
			cs.setTimestamp(3, process.getCreation_date());
			cs.setBytes(4, process.getProcess_img());
			cs.setBytes(5, process.getProcess_anm());
			cs.setBytes(6, process.getProcess_drl());
			cs.setInt(7, process.getProcess_id());
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	public static String deleteProcess(int process_id) throws SQLException{
		try{
			String sqlSentenc = "DELETE FROM process WHERE process_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, process_id);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	public static Object findId(int process_id) throws SQLException {
		FactoryProcess process = null;
		try{
			String sqlSentenc = "SELECT * FROM process WHERE process_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, process_id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				process = new FactoryProcess(rs.getInt("process_id"),rs.getString("process_name"), rs.getInt("process_area"),
						rs.getTimestamp("creation_date"), rs.getBytes("process_img"), rs.getBytes("process_anm"), rs.getBytes("process_drl"));
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return process;
	}

	public static Object findName(String process_name) throws SQLException {
		FactoryProcess process = null;
		try{
			String sqlSentenc = "SELECT * FROM process WHERE process_name = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, process_name);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				process = new FactoryProcess(rs.getInt("process_id"),rs.getString("process_name"), rs.getInt("process_area"),
						rs.getTimestamp("creation_date"), rs.getBytes("process_img"), rs.getBytes("process_anm"), rs.getBytes("process_drl"));
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return process;
	}

	public static Object getProcess() throws SQLException{
		ArrayList<FactoryProcess> processList = new ArrayList<FactoryProcess>();
		try{
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM process");
			while(rs.next()){
				FactoryProcess process = new FactoryProcess(rs.getInt("process_id"),rs.getString("process_name"), rs.getInt("process_area"),
						rs.getTimestamp("creation_date"), rs.getBytes("process_img"), rs.getBytes("process_anm"), rs.getBytes("process_drl"));
				processList.add(process);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return processList;
	}
	
	public static Object findArea(int process_area) throws SQLException{
		ArrayList<FactoryProcess> processList = new ArrayList<FactoryProcess>();
		try{
			String sqlSentenc = "SELECT * FROM process WHERE process_area = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, process_area);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				FactoryProcess process = new FactoryProcess(rs.getInt("process_id"),rs.getString("process_name"), rs.getInt("process_area"),
						rs.getTimestamp("creation_date"), rs.getBytes("process_img"), rs.getBytes("process_anm"), rs.getBytes("process_drl"));
				processList.add(process);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return processList;
	}

	public static Object findUser(int user_id) throws SQLException{
		ArrayList<FactoryProcess> processList = new ArrayList<FactoryProcess>();
		try{
			String sqlSentenc = "SELECT * FROM userProcess WHERE user_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, user_id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				FactoryProcess process = (FactoryProcess) findId(rs.getInt("process_id"));
				processList.add(process);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return processList;
	}
}
