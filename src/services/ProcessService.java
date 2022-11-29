package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import classes.Process;
import classes.ProcessConfiguration;
import classes.User;

public class ProcessService {

	public static Object newProcess(Process process, String user_nick, Timestamp date) throws SQLException{
		int id = -1;
		try{
			String sqlSentenc = "{call public.new_process_only(?,?,?,?,?,?,?,?)}";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			//process
			cs.setString(1, process.getProcess_name());
			cs.setBytes(2, process.getProcess_img());
			cs.setBytes(3, process.getProcess_anm());
			cs.setBytes(4, process.getProcess_drl());
			cs.setInt(5, process.getProcess_area());
			//trace
			cs.setString(6, user_nick);
			cs.setString(7, "creó el proceso");
			cs.setTimestamp(8, date);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
		}catch(Exception e){
			return e.getMessage();
		}
		return id;
	}

	public static String updateProcess(Process process, ProcessConfiguration config, String user_nick, Timestamp date) throws SQLException{
		try{
			String sqlSentenc = "DELETE FROM process WHERE process_id = ?" +
					"INSERT INTO process VALUES(?,?,?,?,?,?);" +
					"INSERT INTO process_configuration VALUES (DEFAULT,?,?,?,?,?,?,?);" + 
					"INSERT INTO trace VALUES (DEFAULT,?,?,?,?);";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, process.getProcess_id());
			//process
			cs.setInt(2, process.getProcess_id());
			cs.setString(3, process.getProcess_name());
			cs.setBytes(4, process.getProcess_img());
			cs.setBytes(5, process.getProcess_anm());
			cs.setBytes(6, process.getProcess_drl());
			cs.setInt(7, process.getProcess_area());
			//configuration
			cs.setInt(8, config.getTime_limit());
			cs.setInt(9, config.getCant_questions());
			cs.setInt(10, config.getCant_aprov());
			cs.setString(11, process.getProcess_name());
			cs.setString(12, process.getProcess_name());
			cs.setString(13, process.getProcess_name());
			//trace
			cs.setString(14, user_nick);
			cs.setString(15, "modificó el proceso");
			cs.setString(16, process.getProcess_name());
			cs.setTimestamp(17, date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	public static String deleteProcess(int process_id, String user_nick, String process_name, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "DELETE FROM process WHERE process_id = ?;"
					+ "INSERT INTO trace VALUES (DEFAULT,?,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, process_id);
			cs.setString(2, user_nick);
			cs.setString(3, "eliminó el proceso");
			cs.setString(4, process_name);
			cs.setTimestamp(5, trace_date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	public static Process searchId(int process_id) throws SQLException {
		Process process = null;
		try{
			String sqlSentenc = "SELECT * FROM process WHERE process_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, process_id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				process = new Process(rs.getInt("process_id"),rs.getString("process_name"), rs.getInt("process_area"),
						rs.getBytes("process_anm"), rs.getBytes("process_drl"), rs.getBytes("process_img"));
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return process;
	}

	public static ArrayList<Process> getProcess(){
		ArrayList<Process> processList = new ArrayList<Process>();
		try{
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM process");
			while(rs.next()){
				Process process = new Process(rs.getInt("process_id"),rs.getString("process_name"), rs.getInt("process_area"),
						rs.getBytes("process_img"), rs.getBytes("process_anm"), rs.getBytes("process_drl"));
				processList.add(process);
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return processList;
	}

	public static ArrayList<Process> searchArea(int process_area) throws SQLException{
		ArrayList<Process> processList = new ArrayList<Process>();
		try{
			String sqlSentenc = "SELECT * FROM process WHERE process_area = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, process_area);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				Process process = new Process(rs.getInt("process_id"),rs.getString("process_name"), rs.getInt("process_area"),
						rs.getBytes("process_img"), rs.getBytes("process_anm"), rs.getBytes("process_drl"));
				processList.add(process);
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return processList;
	}

	public static String insertUsers(ArrayList<User> users, int process, ProcessConfiguration config){
		try{
			String sqlSentenc = "INSERT INTO process_configuration VALUES (DEFAULT,?,?,?,?,?,?,?);"
					+ "INSERT INTO user_process";
			for(int i=0; i<users.size(); i++){
				sqlSentenc = sqlSentenc + " VALUES(DEFAULT,?,?),";
			}
			sqlSentenc = sqlSentenc.substring(0, sqlSentenc.length()-1) + ";";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			//configuration
			int cont = 1;
			cs.setInt(cont, process);
			cont=cont +1;
			cs.setInt(cont, config.getTime_limit());
			cont=cont +1;
			cs.setInt(cont, config.getCant_questions());
			cont=cont +1;
			cs.setInt(cont, config.getCant_aprov());
			cont=cont +1;
			cs.setString(cont, config.getType_var());
			cont=cont +1;
			cs.setString(cont, config.getType_cause());
			cont=cont +1;
			cs.setString(cont, config.getType_rec());
			cont=cont +1;
			for(int i=0; i<users.size(); i++){
				cs.setInt(cont, users.get(i).getUser_id());
				cont=cont +1;
				cs.setInt(cont, process);
				cont=cont +1;
			}
			cs.execute();
			cs.close();
		}catch(Exception e){
			return "insertUsers: " + e.getMessage();
		}
		return null;
	}
	
	public static ArrayList<ProcessConfiguration> getProcessConf(){
		ArrayList<ProcessConfiguration> processList = new ArrayList<ProcessConfiguration>();
		try{
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM process_configuration");
			while(rs.next()){
				ProcessConfiguration process = new ProcessConfiguration(rs.getInt("table_id"),rs.getInt("process_id"), rs.getInt("time_limit"),
						rs.getInt("cant_questions"), rs.getInt("cant_aprov"), rs.getString("type_var"), rs.getString("type_cause"), rs.getString("type_rec"));
				processList.add(process);
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return processList;
	}
}
