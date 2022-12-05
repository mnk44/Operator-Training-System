package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import classes.Area;

public class AreaService {

	public static Object newArea(String area_name, String user_nick, Timestamp date) throws SQLException{
		int id = -1;
		try{
			String sqlSentenc = "{call public.new_area(?,?,?,?)}";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, area_name);
			cs.setString(2, user_nick);
			cs.setString(3, "creó el área");
			cs.setTimestamp(4, date);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
		}catch(Exception e){
			return e.getMessage();
		}
		return id;
	}
	
	public static String updateArea(int area_id, String area_name, String user_nick, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "UPDATE area SET name_area = ? WHERE id_area = ?;"
					+ "INSERT INTO trace VALUES (DEFAULT,?,?,?,?);";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, area_name);
			cs.setInt(2, area_id);
			cs.setString(3, user_nick);
			cs.setString(4, "modificó el área");
			cs.setString(5, area_name);
			cs.setTimestamp(6, trace_date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static String deleteArea(int area_id, String user_nick, String area_name, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "DELETE FROM area WHERE id_area = ?;"
					+ "INSERT INTO trace VALUES (DEFAULT,?,?,?,?);";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, area_id);
			cs.setString(2, user_nick);
			cs.setString(3, "eliminó el área");
			cs.setString(4, area_name);
			cs.setTimestamp(5, trace_date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	public static Object searchId(int area_id) throws SQLException {
		Area area = null;
		try{
			String sqlSentenc = "SELECT * FROM area WHERE id_area = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, area_id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				area = new Area(rs.getInt("id_area"),rs.getString("name_area"));
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return area;
	}
	
	public static ArrayList<Area> getAreas(){
		ArrayList<Area> areasList = new ArrayList<Area>();
		try{
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM area");
			while(rs.next()){
				Area area = new Area(rs.getInt("id_area"),rs.getString("name_area"));
				areasList.add(area);
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return areasList;
	}
}
