package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.Train;
import contentClass.Process;

public class TrainService {
	
	public static String createTrain(Train train) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO process VALUES (DEFAULT,?,?,?,?,?,?,?,?)";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, train.getOperator());
			cs.setFloat(2, train.getResult());
			cs.setFloat(3, train.getVar_result());
			cs.setFloat(4, train.getCause_result());
			cs.setFloat(5, train.getRec_result());
			cs.setTimestamp(6, train.getStart());
			cs.setTimestamp(7, train.getFinish());
			cs.setInt(8, train.getProcess());
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static Train findByProcs(int id_process, int id_opr) throws SQLException {
		Train t = null;
		try{
			String sqlSentenc = "SELECT * FROM training_process WHERE process = ? and operator = ?";
			CallableStatement cs = ServiceConnection.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, id_process);
			cs.setInt(2, id_opr);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				t = new Train(rs.getInt("id_training"), rs.getInt("operator"), rs.getFloat("result"), 
						rs.getFloat("var_result"), rs.getFloat("cause_result"), rs.getFloat("rec_result"),
						rs.getTimestamp("start_date"), rs.getTimestamp("ending_date"), rs.getInt("process"));
			}
		}catch (Exception e){
			System.out.println("Error en findbyPrcs: " + e.getMessage());
		}
		return t;
	}

}
