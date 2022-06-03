package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import contentClass.Area;


/**
 * Permite establecer la conexion directa con la base de datos 
 * para el trabajo de las areas
 * 
 * @author Mnk
 *
 */

public class AreaService {
	
	//introducir nueva area en la basse de datos a partir de su nombre
	public static String createArea(String name_area) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO area VALUES (DEFAULT,?)";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1,name_area);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	//modificar nombre del area
	public static String updateArea(int id_area, String name_area) throws SQLException{
		try{
			String sqlSentenc = "UPDATE area SET name_area = ? WHERE id_area = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name_area);
			cs.setInt(2, id_area);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	//borrar area a partir de su id
	public static String deleteArea(int id_area) throws SQLException{
		try{
			String sqlSentenc = "DELETE FROM area WHERE id_area = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id_area);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	//encontrar un area por su id
		public static Area findById(int id_area) throws SQLException {
			Area area = null;
			try{
				String sqlSentenc = "SELECT * FROM area WHERE id_area = ?";
				CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
				cs.setInt(1, id_area);
				ResultSet rs = cs.executeQuery();
				if(rs.next()){
					area = new Area(rs.getInt("id_area"),rs.getString("name_area"));
				}
			}catch (Exception e){
				System.out.println("Error: " + e.getMessage());
			}
			return area;
		}
	
	//encontrar un area por su nombre
	public static Area findByName(String name_area) throws SQLException {
		Area area = null;
		try{
			String sqlSentenc = "SELECT * FROM area WHERE name_area = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name_area);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				area = new Area(rs.getInt("id_area"),rs.getString("name_area"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return area;
	}
	
	//obtener todas las areas
	public static ArrayList<Area> getAreas() throws SQLException{
		ArrayList<Area> areas = new ArrayList<Area>();
		try{
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM area");
			while(rs.next()){
				Area area = new Area(rs.getInt("id_area"),rs.getString("name_area"));
				areas.add(area);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return areas;
	}
}
