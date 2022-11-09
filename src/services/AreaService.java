package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import classes.Area;

public class AreaService {

	public static int newArea(String area_name, String user_nick, String action,
			String action_class, Timestamp date) throws SQLException{
		int id = -1;
		try{
			String sqlSentenc = "{call public.new_area(?,?,?,?)}";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, area_name);
			cs.setString(2, user_nick);
			cs.setString(3, action);
			cs.setTimestamp(4, date);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
		}catch(Exception e){
			e.getMessage();
		}
		return id;
	}
	
	public static void updateArea(int area_id, String area_name, String user_nick, String trace_accion, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "UPDATE area SET name_area = ? WHERE id_area = ?;"
					+ "INSERT INTO trace VALUES (DEFAULT,?,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, area_name);
			cs.setInt(2, area_id);
			cs.setString(3, user_nick);
			cs.setString(4, "modificó un área");
			cs.setString(5, area_name);
			cs.setTimestamp(6, trace_date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	public static void deleteArea(int area_id, String user_nick, String trace_accion,
		 String area_name, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "DELETE FROM area WHERE id_area = ?;"
					+ "INSERT INTO trace VALUES (DEFAULT,?,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, area_id);
			cs.setString(2, user_nick);
			cs.setString(3, "eliminó un área");
			cs.setString(4, area_name);
			cs.setTimestamp(5, trace_date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			e.getMessage();
		}
	}

	public static Area searchId(int area_id) throws SQLException {
		Area area = null;
		try{
			String sqlSentenc = "SELECT * FROM area WHERE id_area = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, area_id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				area = new Area(rs.getInt("id_area"),rs.getString("name_area"),rs.getBoolean("area_empty"));
			}
		}catch (Exception e){
			e.getMessage();
		}
		return area;
	}
	
	public static ArrayList<Area> getAreas() throws SQLException{
		ArrayList<Area> areasList = new ArrayList<Area>();
		try{
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM area");
			while(rs.next()){
				Area area = new Area(rs.getInt("id_area"),rs.getString("name_area"),rs.getBoolean("area_empty"));
				areasList.add(area);
			}
		}catch (Exception e){
			e.getMessage();
		}
		return areasList;
	}
}
