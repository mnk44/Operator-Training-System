package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import classes.User;

public class UserService {
	
	public static Object newUser(User user, String user_nick, Timestamp date) throws SQLException{
		int id = -1;
		try{
			String sqlSentenc = "{call public.new_user(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, user.getUser_name());
			cs.setInt(2, user.getUser_sex());
			cs.setString(3, user.getUser_card());
			cs.setInt(4, user.getUser_experience());
			cs.setString(5, user.getUser_level());
			cs.setInt(6, user.getUser_boss());
			cs.setString(7, user.getUser_nick());
			cs.setString(8, user.getUser_pass());
			cs.setBoolean(9, user.isUser_active());
			cs.setInt(10, user.getUser_area());
			cs.setInt(11, user.getUser_rol());
			cs.setString(12, user_nick);
			cs.setString(13, "creó el usuario");
			cs.setTimestamp(14, date);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
		}catch(Exception e){
			return e.getMessage();
		}
		return id;
	}
	
	public static String updateUser(User user, String user_nick, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "UPDATE users SET user_name = ?, user_sex = ?, user_card = ?, user_experience = ?,"
					+ "user_level = ?, user_boss = ?, user_nick = ?, user_pass = ?, user_active = ?, user_area = ?,"
					+ "user_rol = ? WHERE user_id = ?;"
					+ "INSERT INTO trace VALUES (DEFAULT,?,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, user.getUser_name());
			cs.setInt(2, user.getUser_sex());
			cs.setString(3, user.getUser_card());
			cs.setInt(4, user.getUser_experience());
			cs.setString(5, user.getUser_level());
			cs.setInt(6, user.getUser_boss());
			cs.setString(7, user.getUser_nick());
			cs.setString(8, user.getUser_pass());
			cs.setBoolean(9, user.isUser_active());
			cs.setInt(10, user.getUser_area());
			cs.setInt(11, user.getUser_rol());
			cs.setString(12, user_nick);
			cs.setString(13, "modificó el usuario");
			cs.setString(14, user.getUser_nick());
			cs.setTimestamp(15, trace_date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static String changePassword(int user_id, String pass, String user_nick, String nick, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "UPDATE users SET user_pass = ? WHERE user_id = ?;"
					+ "INSERT INTO trace VALUES (DEFAULT,?,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, pass);
			cs.setInt(2, user_id);
			cs.setString(3, user_nick);
			cs.setString(4, "cambió la contraseña de");
			cs.setString(5, nick);
			cs.setTimestamp(6, trace_date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static String changeStatus(int user_id, boolean status, String user_nick, String nick, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "UPDATE users SET user_active = ? WHERE user_id = ?;"
					+ "INSERT INTO trace VALUES (DEFAULT,?,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setBoolean(1, status);
			cs.setInt(2, user_id);
			cs.setString(3, user_nick);
			cs.setString(4, "cambió el estado de");
			cs.setString(5, nick);
			cs.setTimestamp(6, trace_date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	public static Object searchId(int user_id) throws SQLException {
		User user = null;
		try{
			String sqlSentenc = "SELECT * FROM users WHERE user_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, user_id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){				
				user = new User(rs.getInt("user_id"),rs.getString("user_name"),rs.getInt("user_sex"),rs.getString("user_card"),
						rs.getInt("user_experience"),rs.getString("user_level"),rs.getInt("user_boss"),rs.getString("user_nick"),
						rs.getString("user_pass"),rs.getBoolean("user_active"),rs.getInt("user_area"), rs.getInt("user_rol"));
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return user;
	}
	
	public static Object searchNick(String user_nick) throws SQLException {
		User user = null;
		try{
			String sqlSentenc = "SELECT * FROM users WHERE user_nick = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, user_nick);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				user = new User(rs.getInt("user_id"),rs.getString("user_name"),rs.getInt("user_sex"),rs.getString("user_card"),
						rs.getInt("user_experience"),rs.getString("user_level"),rs.getInt("user_boss"),rs.getString("user_nick"),
						rs.getString("user_pass"),rs.getBoolean("user_active"),rs.getInt("user_area"), rs.getInt("user_rol"));
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return user;
	}
	
	public static Object getUsers() throws SQLException{
		ArrayList<User> usersList = new ArrayList<User>();
		try{
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM users");
			while(rs.next()){		
				User user = new User(rs.getInt("user_id"),rs.getString("user_name"),rs.getInt("user_sex"),rs.getString("user_card"),
						rs.getInt("user_experience"),rs.getString("user_level"),rs.getInt("user_boss"),rs.getString("user_nick"),
						rs.getString("user_pass"),rs.getBoolean("user_active"),rs.getInt("user_area"), rs.getInt("user_rol"));
				usersList.add(user);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return usersList;
	}
	
	public static Object getUsersArea(int user_area) throws SQLException{
		ArrayList<User> usersList = new ArrayList<User>();
		try{
			String sqlSentenc = "SELECT * FROM users WHERE user_area = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, user_area);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				User user = new User(rs.getInt("user_id"),rs.getString("user_name"),rs.getInt("user_sex"),rs.getString("user_card"),
						rs.getInt("user_experience"),rs.getString("user_level"),rs.getInt("user_boss"),rs.getString("user_nick"),
						rs.getString("user_pass"),rs.getBoolean("user_active"),rs.getInt("user_area"), rs.getInt("user_rol"));
				usersList.add(user);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return usersList;
	}
}
