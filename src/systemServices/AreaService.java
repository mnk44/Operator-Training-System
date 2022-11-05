package systemServices;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import systemClass.Area;
import systemEnums.AccionTrace;
import systemEnums.SystemClass;

public class AreaService {
	
	public static String newArea(String area_name, int trace_user, AccionTrace trace_accion,
			SystemClass trace_class, String trace_class_id, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "{call public.insert_area(?,?,?,?,?,?)}";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, area_name);
			cs.setInt(2, trace_user);
			cs.setString(3, trace_accion.toString());
			cs.setString(4, trace_class.toString());
			cs.setString(5, trace_class_id);
			cs.setTimestamp(6, trace_date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static String updateArea(int area_id, String area_name, int trace_user, AccionTrace trace_accion,
			SystemClass trace_class, String trace_class_id, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "{call public.update_area(?,?,?,?,?,?,?)}";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, area_name);
			cs.setInt(2, area_id);
			cs.setInt(3, trace_user);
			cs.setString(4, trace_accion.toString());
			cs.setString(5, trace_class.toString());
			cs.setString(6, trace_class_id);
			cs.setTimestamp(7, trace_date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static String deleteArea(int area_id, int trace_user, AccionTrace trace_accion,
			SystemClass trace_class, String trace_class_id, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "{call public.delete_area(?,?,?,?,?,?)}";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, area_id);
			cs.setInt(2, trace_user);
			cs.setString(3, trace_accion.toString());
			cs.setString(4, trace_class.toString());
			cs.setString(5, trace_class_id);
			cs.setTimestamp(6, trace_date);
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
