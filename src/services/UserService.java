package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import classes.User;
import enums.AccionTrace;
import enums.RolesTypes;
import enums.SchoolarLevel;
import enums.SystemClass;

public class UserService {
	
	public static String newUser(String user_name, String user_card,
			SchoolarLevel user_academic, int user_experience,
			int user_position_years, String user_nick, String user_password,
			boolean user_active, int user_area, RolesTypes user_rol, int trace_user, AccionTrace trace_accion,
			SystemClass trace_class, String trace_class_id, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO people VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?);"
					+ "INSERT INTO system_trace VALUES (DEFAULT,?,?,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, user_name);
			cs.setString(2, user_card);
			cs.setString(3, user_academic.toString().replace("_" , ""));
			cs.setInt(4, user_experience);
			cs.setInt(5, user_position_years);
			cs.setString(6, user_nick);
			cs.setString(7, user_password);
			cs.setBoolean(8, user_active);
			cs.setInt(9, user_area);
			cs.setInt(10, user_rol.ordinal() + 1);
			cs.setInt(11, trace_user);
			cs.setString(12, trace_accion.toString());
			cs.setString(13, trace_class.toString());
			cs.setString(14, trace_class_id);
			cs.setTimestamp(15, trace_date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static String updateUser(User user, int trace_user, AccionTrace trace_accion,
			SystemClass trace_class, String trace_class_id, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "UPDATE people SET user_name = ?, user_card = ?, user_academic = ?,"
					+ "user_experience = ?, user_position_years = ?, user_nick = ?,"
					+ "user_password = ?, user_active = ?, user_area = ?, user_rol = ? WHERE user_id = ?;"
					+ "INSERT INTO system_trace VALUES (DEFAULT,?,?,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, user.getUser_name());
			cs.setString(2, user.getUser_card());
			cs.setString(3, user.getUser_academic().toString().replace("_" , ""));
			cs.setInt(4, user.getUser_experience());
			cs.setInt(5, user.getUser_position_years());
			cs.setString(6, user.getUser_nick());
			cs.setString(7, user.getUser_password());
			cs.setBoolean(8, user.isUser_active());
			cs.setInt(9, user.getUser_area());
			cs.setInt(10, user.getUser_rol().ordinal() + 1);
			cs.setInt(11, user.getUser_id());
			cs.setInt(12, trace_user);
			cs.setString(13, trace_accion.toString());
			cs.setString(14, trace_class.toString());
			cs.setString(15, trace_class_id);
			cs.setTimestamp(16, trace_date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static String changePassword(int user_id, String new_pass, int trace_user, String trace_accion, String trace_class_id, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "UPDATE people SET user_password = ? WHERE user_id = ?;"
					+ "INSERT INTO system_trace VALUES (DEFAULT,?,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, new_pass);
			cs.setInt(2, user_id);
			cs.setInt(3, trace_user);
			cs.setString(4, trace_accion);
			cs.setString(5, trace_class_id);
			cs.setTimestamp(6, trace_date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	public static Object findId(int user_id) throws SQLException {
		User user = null;
		try{
			String sqlSentenc = "SELECT * FROM people WHERE user_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, user_id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				RolesTypes rol = null;
				if(rs.getInt("user_rol") == 1){
					rol = RolesTypes.Administrador;
				}else if(rs.getInt("user_rol") == 2){
					rol = RolesTypes.Jefe_de_área;
				}else{
					rol = RolesTypes.Operario;
				}
				
				user = new User(rs.getInt("user_id"),rs.getString("user_name"),rs.getString("user_card"),
						SchoolarLevel.valueOf(rs.getString("user_academic")),rs.getInt("user_experience"),rs.getInt("user_position_years"),
						rs.getString("user_nick"),rs.getString("user_password"),rs.getBoolean("user_active"),
						rs.getInt("user_area"), rol);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return user;
	}
	
	public static Object findCard(String user_card) throws SQLException {
		User user = null;
		try{
			String sqlSentenc = "SELECT * FROM people WHERE user_card = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, user_card);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				RolesTypes rol = null;
				if(rs.getInt("user_rol") == 1){
					rol = RolesTypes.Administrador;
				}else if(rs.getInt("user_rol") == 2){
					rol = RolesTypes.Jefe_de_área;
				}else{
					rol = RolesTypes.Operario;
				}
				
				user = new User(rs.getInt("user_id"),rs.getString("user_name"),rs.getString("user_card"),
						SchoolarLevel.valueOf(rs.getString("user_academic")),rs.getInt("user_experience"),rs.getInt("user_position_years"),
						rs.getString("user_nick"),rs.getString("user_password"),rs.getBoolean("user_active"),
						rs.getInt("user_area"), rol);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return user;
	}
	
	public static Object findNick(String user_nick) throws SQLException {
		User user = null;
		try{
			String sqlSentenc = "SELECT * FROM people WHERE user_nick = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, user_nick);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				RolesTypes rol = null;
				if(rs.getInt("user_rol") == 1){
					rol = RolesTypes.Administrador;
				}else if(rs.getInt("user_rol") == 2){
					rol = RolesTypes.Jefe_de_área;
				}else{
					rol = RolesTypes.Operario;
				}
				
				user = new User(rs.getInt("user_id"),rs.getString("user_name"),rs.getString("user_card"),
						SchoolarLevel.valueOf(rs.getString("user_academic")),rs.getInt("user_experience"),rs.getInt("user_position_years"),
						rs.getString("user_nick"),rs.getString("user_password"),rs.getBoolean("user_active"),
						rs.getInt("user_area"), rol);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return user;
	}
	
	public static Object getUsers() throws SQLException{
		ArrayList<User> usersList = new ArrayList<User>();
		try{
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM people");
			while(rs.next()){
				RolesTypes rol = null;
				if(rs.getInt("user_rol") == 1){
					rol = RolesTypes.Administrador;
				}else if(rs.getInt("user_rol") == 2){
					rol = RolesTypes.Jefe_de_área;
				}else{
					rol = RolesTypes.Operario;
				}
				
				User user = new User(rs.getInt("user_id"),rs.getString("user_name"),rs.getString("user_card"),
						SchoolarLevel.valueOf(rs.getString("user_academic")),rs.getInt("user_experience"),rs.getInt("user_position_years"),
						rs.getString("user_nick"),rs.getString("user_password"),rs.getBoolean("user_active"),
						rs.getInt("user_area"), rol);
				
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
			String sqlSentenc = "SELECT * FROM people WHERE user_area = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, user_area);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				RolesTypes rol = null;
				if(rs.getInt("user_rol") == 1){
					rol = RolesTypes.Administrador;
				}else if(rs.getInt("user_rol") == 2){
					rol = RolesTypes.Jefe_de_área;
				}else{
					rol = RolesTypes.Operario;
				}
				
				User user = new User(rs.getInt("user_id"),rs.getString("user_name"),rs.getString("user_card"),
						SchoolarLevel.valueOf(rs.getString("user_academic")),rs.getInt("user_experience"),rs.getInt("user_position_years"),
						rs.getString("user_nick"),rs.getString("user_password"),rs.getBoolean("user_active"),
						rs.getInt("user_area"), rol);
				
				usersList.add(user);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return usersList;
	}
	
	public static Object getUsersName(String user_name, int user_area) throws SQLException{
		ArrayList<User> usersList = new ArrayList<User>();
		try{
			String sqlSentenc = "SELECT * FROM people WHERE user_name = ? and user_area = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, user_name);
			cs.setInt(2, user_area);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				RolesTypes rol = null;
				if(rs.getInt("user_rol") == 1){
					rol = RolesTypes.Administrador;
				}else if(rs.getInt("user_rol") == 2){
					rol = RolesTypes.Jefe_de_área;
				}else{
					rol = RolesTypes.Operario;
				}
				
				User user = new User(rs.getInt("user_id"),rs.getString("user_name"),rs.getString("user_card"),
						SchoolarLevel.valueOf(rs.getString("user_academic")),rs.getInt("user_experience"),rs.getInt("user_position_years"),
						rs.getString("user_nick"),rs.getString("user_password"),rs.getBoolean("user_active"),
						rs.getInt("user_area"), rol);
				
				usersList.add(user);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return usersList;
	}
}
