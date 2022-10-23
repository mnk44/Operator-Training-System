package systemServices;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import systemClass.Area;

public class AreaService {
	
	public static String newArea(String area_name) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO area VALUES (DEFAULT,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, area_name);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static String updateArea(int area_id, String area_name) throws SQLException{
		try{
			String sqlSentenc = "UPDATE area SET area_name = ? WHERE area_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, area_name);
			cs.setInt(2, area_id);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static String deleteArea(int area_id) throws SQLException{
		try{
			String sqlSentenc = "DELETE FROM area WHERE area_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, area_id);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	public static Object findId(int area_id) throws SQLException {
		Area area = null;
		try{
			String sqlSentenc = "SELECT * FROM area WHERE area_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, area_id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				area = new Area(rs.getInt("area_id"),rs.getString("area_name"));
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return area;
	}
	
	public static Object findName(String area_name) throws SQLException {
		Area area = null;
		try{
			String sqlSentenc = "SELECT * FROM area WHERE area_name = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, area_name);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				area = new Area(rs.getInt("area_id"),rs.getString("area_name"));
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return area;
	}
	
	public static Object getAreas() throws SQLException{
		ArrayList<Area> areasList = new ArrayList<Area>();
		try{
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM area");
			while(rs.next()){
				Area area = new Area(rs.getInt("area_id"),rs.getString("area_name"));
				areasList.add(area);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return areasList;
	}
}
