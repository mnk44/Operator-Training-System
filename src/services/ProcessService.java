package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import contentClass.Process;

/**
 * Permite establecer la conexion directa con la base de datos 
 * para el trabajo de los procesos
 * 
 * @author Mnk
 *
 */

public class ProcessService {

	//introducir nuevo proceso en la basse de datos
	public static String createProcess(Process process) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO process VALUES (DEFAULT,?,?,?,?,?,?)";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, process.getName_process());
			cs.setTimestamp(2, process.getCreation_date());
			cs.setBytes(3, process.getProcess_image());
			cs.setBytes(4, process.getAnm_file());
			cs.setBytes(5, process.getDrl_file());
			cs.setInt(6, process.getArea());
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	//modificar nombre del proceso
	public static String updateProcess(Process process) throws SQLException{
		try{
			String sqlSentenc = "UPDATE process SET name_process = ?, creation_date = ?, process_image = ?"
					+ "anm_file = ?, drl_file = ?, area = ? WHERE id_process = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, process.getName_process());
			cs.setTimestamp(2, process.getCreation_date());
			cs.setBytes(3, process.getProcess_image());
			cs.setBytes(4, process.getAnm_file());
			cs.setBytes(5, process.getDrl_file());
			cs.setInt(6, process.getArea());
			cs.setInt(7, process.getId_process());
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	//borrar proceso a partir de su id
	public static String deleteProcess(int id_process) throws SQLException{
		try{
			String sqlSentenc = "DELETE FROM process WHERE id_process = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id_process);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	//encontrar un proceso por su id
	public static Process findById(int id_process) throws SQLException {
		Process process = null;
		try{
			String sqlSentenc = "SELECT * FROM process WHERE id_process = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id_process);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				process = new Process(rs.getInt("id_process"),rs.getString("name_process"), rs.getTimestamp("creation_date"),
						rs.getBytes("process_image"), rs.getBytes("anm_file"), rs.getBytes("drl_file"), rs.getInt("area"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return process;
	}

	//encontrar un proceso por su nombre
	public static Process findByName(String name_process) throws SQLException {
		Process process = null;
		try{
			String sqlSentenc = "SELECT * FROM process WHERE name_process = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, name_process);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				process = new Process(rs.getInt("id_process"),rs.getString("name_process"), rs.getTimestamp("creation_date"),
						rs.getBytes("process_image"), rs.getBytes("anm_file"), rs.getBytes("drl_file"), rs.getInt("area"));
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return process;
	}

	//obtener todas los procesos
	public static ArrayList<Process> getProcess() throws SQLException{
		ArrayList<Process> ps = new ArrayList<Process>();
		try{
			ResultSet rs = ServiceConnection.getConnection().createStatement().executeQuery("SELECT * FROM process");
			while(rs.next()){
				Process process = new Process(rs.getInt("id_process"),rs.getString("name_process"), rs.getTimestamp("creation_date"),
						rs.getBytes("process_image"), rs.getBytes("anm_file"), rs.getBytes("drl_file"), rs.getInt("area"));
				ps.add(process);
			}
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return ps;
	}
}
