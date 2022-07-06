package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.Rol;
import utils.SchoolLevel;
import logic.Encrypting;
import contentClass.User;

/**
 * Permite establecer la conexion directa con la base de datos 
 * para el trabajo de los usuarios
 * 
 * @author Mnk
 *
 */

public class UserService {

	//introducir nuevo usuario en el sistema
	public static String createUser(User user) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO user_table VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?)";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1,user.getName_user());
			cs.setString(2,user.getIdentity_card());
			cs.setString(3,user.getSchool_level().name());
			cs.setInt(4,user.getExperience());
			cs.setInt(5,user.getPosition_years());
			cs.setString(6,user.getNick_name());
			cs.setString(7,user.getPassword());
			cs.setInt(8,user.getArea());
			cs.setInt(9,user.getRol().ordinal()+1);
			cs.setBoolean(10,user.getSleep());
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	//modificar usuario
	public static String updateUser(User user) throws SQLException{
		try{
			String sqlSentenc = "UPDATE user_table SET name_user = ?, identity_card = ?, school_level = ?, experience = ?,"
					+ "position_years = ?, nick_name = ?, password = ?, area = ?, rol = ?, sleep = ? WHERE id_user = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1,user.getName_user());
			cs.setString(2,user.getIdentity_card());
			cs.setString(3,user.getSchool_level().name());
			cs.setInt(4,user.getExperience());
			cs.setInt(5,user.getPosition_years());
			cs.setString(6,user.getNick_name());
			cs.setString(7,user.getPassword());
			cs.setInt(8,user.getArea());
			cs.setInt(9,user.getRol().ordinal()+1);
			cs.setBoolean(10, user.getSleep());
			cs.setInt(11, user.getId_user());
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	//modificar la contraseña
	public static String changePassword(int id_user, String password) throws SQLException{
		try{
			String sqlSentenc = "UPDATE user_table SET password = ? WHERE id_user = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, Encrypting.getMd5(password));
			cs.setInt(2, id_user);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	//dormir usuario a partir de su id
	public static String sleepUser(int id_user) throws SQLException{
		try{
			String sqlSentenc = "UPDATE user_table SET sleep = true WHERE id_user = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id_user);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	//despertar usuario a partir de su id
	public static String awakeUser(int id_user) throws SQLException{
		try{
			String sqlSentenc = "UPDATE user_table SET sleep = false WHERE id_user = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id_user);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	//encontrar un usuario por su id
	public static User findById(int id_user) throws SQLException {
		User user = null;
		try{
			String sqlSentenc = "SELECT * FROM user_table WHERE id_user = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id_user);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				user = new User(rs.getInt("id_user"),rs.getString("name_user"),rs.getString("identity_card"),
						findSchoolLevel(rs.getString("school_level")), rs.getInt("experience"), rs.getInt("position_years"),
						rs.getString("nick_name"), rs.getString("password"), rs.getInt("area"), findRol(rs.getInt("rol")), rs.getBoolean("sleep"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return user;
	}

	//encontrar un usuario por su nombre
	public static User findByName(String name_user, int area) throws SQLException {
		User user = null;
		try{
			String sqlSentenc = "SELECT * FROM user_table WHERE name_user = ? and area = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name_user);
			cs.setInt(2, area);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				user = new User(rs.getInt("id_user"),rs.getString("name_user"),rs.getString("identity_card"),
						findSchoolLevel(rs.getString("school_level")), rs.getInt("experience"), rs.getInt("position_years"),
						rs.getString("nick_name"), rs.getString("password"), rs.getInt("area"), findRol(rs.getInt("rol")), rs.getBoolean("sleep"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return user;
	}

	//encontrar un usuario por su nombre
	public static User findByCard(String identity_card) throws SQLException {
		User user = null;
		try{
			String sqlSentenc = "SELECT * FROM user_table WHERE identity_card = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, identity_card);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				user = new User(rs.getInt("id_user"),rs.getString("name_user"),rs.getString("identity_card"),
						findSchoolLevel(rs.getString("school_level")), rs.getInt("experience"), rs.getInt("position_years"),
						rs.getString("nick_name"), rs.getString("password"), rs.getInt("area"), findRol(rs.getInt("rol")), rs.getBoolean("sleep"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return user;
	}

	//encontrar un usuario por su nombre
	public static User findByNick(String nick_name) throws SQLException {
		User user = null;
		try{
			String sqlSentenc = "SELECT * FROM user_table WHERE nick_name = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, nick_name);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				user = new User(rs.getInt("id_user"),rs.getString("name_user"),rs.getString("identity_card"),
						findSchoolLevel(rs.getString("school_level")), rs.getInt("experience"), rs.getInt("position_years"),
						rs.getString("nick_name"), rs.getString("password"), rs.getInt("area"), findRol(rs.getInt("rol")), rs.getBoolean("sleep"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return user;
	}

	//obtener todos los usuarios
	public static ArrayList<User> getUsers() throws SQLException{
		ArrayList<User> users = new ArrayList<User>();
		try{
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM user_table");
			while(rs.next()){
				User user =  new User(rs.getInt("id_user"),rs.getString("name_user"),rs.getString("identity_card"),
						findSchoolLevel(rs.getString("school_level")), rs.getInt("experience"), rs.getInt("position_years"),
						rs.getString("nick_name"), rs.getString("password"), rs.getInt("area"), findRol(rs.getInt("rol")), rs.getBoolean("sleep"));
				users.add(user);
			}
		}catch (Exception e){
			System.out.println("Error en getUsers(): " + e.getMessage());
		}
		return users;
	}
	
	//obtener todos los operarios de un Area
		public static ArrayList<User> getOp(int area) throws SQLException{
			ArrayList<User> users = new ArrayList<User>();
			try{
				String sqlSentenc = "SELECT * FROM user_table WHERE area = ? and rol = 3";
				CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
				cs.setInt(1, area);
				ResultSet rs = cs.executeQuery();
				while(rs.next()){
					User user =  new User(rs.getInt("id_user"),rs.getString("name_user"),rs.getString("identity_card"),
							findSchoolLevel(rs.getString("school_level")), rs.getInt("experience"), rs.getInt("position_years"),
							rs.getString("nick_name"), rs.getString("password"), rs.getInt("area"), findRol(rs.getInt("rol")), rs.getBoolean("sleep"));
					users.add(user);
				}
			}catch (Exception e){
				System.out.println("Error en getUsers(): " + e.getMessage());
			}
			return users;
		}

	////////////////////////////////////////////////////////////////////
	public static SchoolLevel findSchoolLevel(String schoolLevel){
		switch (schoolLevel) {
		case "PRIMARIO":
			return SchoolLevel.PRIMARIA;
		case "SECUNDARIO":
			return SchoolLevel.SECUNDARIA;
		case "PREUNIVERSITARIO":
			return SchoolLevel.PREUNIVERSITARIO;
		case "UNIVERSITARIO":
			return SchoolLevel.UNIVERSITARIO;
		case "MÁSTER":
			return SchoolLevel.MÁSTER;
		case "DOCTOR":
			return SchoolLevel.DOCTOR;
		default:
			return SchoolLevel.PRIMARIA;
		}
	}

	public static Rol findRol(int rol){
		switch (rol) {
		case 1:
			return Rol.ADMINISTRADOR;
		case 2:
			return Rol.JEFE_DE_AREA;
		case 3:
			return Rol.OPERARIO;
		default:
			return Rol.OPERARIO;		
		}
	}
}
