package trainerLogic;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import services.ConnectionService;
import services.TrainingService;
import classes.ProcessConfiguration;
import classes.TrainPart;
import classes.Training;

public class InsertTrainPart {
	
	public static void insertPart(TrainPart train, Training t, ProcessConfiguration conf, String nick) throws SQLException{
		int cantTry = t.getCant_try() + 1;
		int aprov = t.getCant_aprov();
		double prom = 0;
		double generalNote = 0;
		int j = 0;
		
		boolean agregar = false;
		boolean suspense = false;
		
		String sqlSentenc = "INSERT INTO train_part VALUES(DEFAULT,?,?,?,?,?);"
				+ "INSERT INTO trace VALUES (DEFAULT,?,?,?,?);"
				+ "UPDATE training SET cant_try = ?, cant_aprov = ? WHERE train_id = ?;";
		int x = (int) train.getFinal_note();
		if(x >= 5){
			aprov = aprov + 1;
			if(aprov == conf.getCant_aprov()){
				aprov = 0;
				cantTry = 0;
				ArrayList<Double> notes = TrainingService.getNotes(t.getTrain_id(), train.getTest_type());
				for(int i=0; i<notes.size(); i++){
					if(notes.get(i) >= 5){
						prom = prom + notes.get(i);
						j = j + 1;
					}
				}
				j = j + 1;
				prom = prom + train.getFinal_note();
				prom = prom/j;
				agregar = true;
				if(train.getTest_type().equals("variable")){
					sqlSentenc = sqlSentenc + "UPDATE training SET var_note = ? WHERE train_id = ?;";
				}else if(train.getTest_type().equals("causa")){
					sqlSentenc = sqlSentenc + "UPDATE training SET cause_note = ? WHERE train_id = ?;";
				}else{
					sqlSentenc = sqlSentenc + "UPDATE training SET rec_note = ?, general_note = ? WHERE train_id = ?;";
					generalNote = (t.getVar_note() + t.getCause_note() + prom)/3;
					suspense = true;
					agregar = false;
				}
			}
		}else if(cantTry == conf.getCant_questions()){
			if(train.getTest_type().equals("variable")){
				sqlSentenc = sqlSentenc + "UPDATE training SET var_note = ?, general_note = ? WHERE train_id = ?;";
			}else if(train.getTest_type().equals("causa")){
				sqlSentenc = sqlSentenc + "UPDATE training SET cause_note = ?, general_note = ? WHERE train_id = ?;";
			}else{
				sqlSentenc = sqlSentenc + "UPDATE training SET rec_note = ?, general_note = ? WHERE train_id = ?;";
				generalNote = (t.getVar_note() + t.getCause_note())/3;
			}
			suspense = true;
		}
		
		try{
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, train.getTraining_id());
			cs.setDouble(2, train.getTime_rest());
			cs.setInt(3, train.getCant_aproved());
			cs.setString(4, train.getTest_type());
			cs.setDouble(5, train.getFinal_note());
			cs.setString(6, nick);
			cs.setString(7, "terminó una etapa del entrenamiento");
			cs.setString(8, Integer.toString(t.getTrain_id()));
			cs.setTimestamp(9, new Timestamp(Calendar.getInstance().getTime().getTime()));
			cs.setInt(10, cantTry);
			cs.setInt(11, aprov);
			cs.setInt(12, t.getTrain_id());
			if(agregar){
				cs.setDouble(13, prom);
				cs.setInt(14, t.getTrain_id());
			}else if(suspense){
				cs.setDouble(13, prom);
				cs.setDouble(14, generalNote);
				cs.setInt(15, t.getTrain_id());
			}
			cs.execute();
			cs.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
