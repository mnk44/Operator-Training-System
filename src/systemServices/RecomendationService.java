package systemServices;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import systemClass.Recomendation;

public class RecomendationService {

	public static String newRecomendation(Recomendation recomendation) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO recomendation VALUES (DEFAULT,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, recomendation.getRecomendation_name());
			cs.setInt(2, recomendation.getRecomendation_process());
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	public static Object findId(int recomendation_id) throws SQLException {
		Recomendation recomendation = null;
		try{
			String sqlSentenc = "SELECT * FROM recomendation WHERE recomendation_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, recomendation_id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				recomendation = new Recomendation(rs.getInt("recomendation_id"),rs.getString("recomendation_name"), rs.getInt("recomendation_process"));
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return recomendation;
	}
	
	public static Object findName(String recomendation_name, int recomendation_process) throws SQLException {
		Recomendation recomendation = null;
		try{
			String sqlSentenc = "SELECT * FROM recomendation WHERE recomendation_name = ? and recomendation_process = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setString(1, recomendation_name);
			cs.setInt(2, recomendation_process);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				recomendation = new Recomendation(rs.getInt("recomendation_id"),rs.getString("recomendation_name"), rs.getInt("recomendation_process"));
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return recomendation;
	}

	public static Object getRecomendations(int process_id) throws SQLException{
		ArrayList<Recomendation> recomendationList = new ArrayList<Recomendation>();
		try{
			String sqlSentenc = "SELECT * FROM recomendation WHERE recomendation_process = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, process_id);
			ResultSet rs = cs.executeQuery();
			while(rs.next()){
				Recomendation recomendation = new Recomendation(rs.getInt("recomendation_id"),rs.getString("recomendation_name"), rs.getInt("recomendation_process"));
				recomendationList.add(recomendation);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return recomendationList;
	}
}
