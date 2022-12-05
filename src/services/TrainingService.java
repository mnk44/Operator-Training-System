package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
			while(rs.next()){
				Training t = new Training(rs.getInt("train_id"), rs.getInt("process_id"), rs.getInt("operator_id"), rs.getInt("cant_try"),
						rs.getDouble("var_note"), rs.getDouble("cause_note"), rs.getDouble("rec_note"), rs.getDouble("general_note"), rs.getInt("cant_aprov"));
				train.add(t);
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return train;
	}
	
	public static Object newTraining(int process_id, int operator_id, String nick, Timestamp date) throws SQLException{
		int id = -1;
		try{
			String sqlSentenc = "{call public.new_trainnig(?,?,?,?,?,?)}";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, process_id);
			cs.setInt(2, operator_id);
			cs.setInt(3, 0);
			cs.setString(4, nick);
			cs.setString(5, "inició un nuevo entrenamiento");
			cs.setTimestamp(6, date);
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
		}catch(Exception e){
			return e.getMessage();
		}
		return id;
	}
	
	public static ArrayList<Double> getNotes(int train, String type) throws SQLException{
		ArrayList<Double> notes = new ArrayList<>();
		try{
			String sqlSentenc = "SELECT * FROM train_part WHERE training_id = ? AND test_type = ?;";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, train);
			cs.setString(2, type);;
			ResultSet rs = cs.executeQuery();
			while(rs.next()){
				notes.add(rs.getDouble("final_note"));
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return notes;
	}
}
