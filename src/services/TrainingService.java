package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Training;

public class TrainingService {

	public static ArrayList<Training> searchOperator(int operator) throws SQLException{
		ArrayList<Training> train = new ArrayList<Training>();
		try{
			String sqlSentenc = "SELECT * FROM training WHERE operator_id = ?";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, operator);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				Training t = new Training(rs.getInt("train_id"), rs.getInt("process_id"), rs.getInt("operator_id"), rs.getInt("cant_try"),
						rs.getDouble("var_note"), rs.getDouble("cause_note"), rs.getDouble("rec_note"), rs.getDouble("general_note"));
				train.add(t);
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return train;
	}
}
