package services;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.AnmFile;
import classes.Cause;
import classes.CauseRecomendation;
import classes.DrlFile;
import classes.Recomendation;
import classes.Variable;
import classes.VariableCause;

public class FileService {

	public static ArrayList<Integer> getIds() {
		ArrayList<Integer> ids = new ArrayList<>();
		
		String sqlSentenc = "SELECT MAX(var_id) FROM variable;";
		ResultSet rs = null;
		try {
			rs = ConnectionService.getConnection().createStatement().executeQuery(sqlSentenc);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.next()){
				ids.add(0, rs.getInt(1));
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			ids.add(0, 0);
		}
		sqlSentenc = "SELECT MAX(cause_id) FROM cause;";
		try {
			rs = ConnectionService.getConnection().createStatement().executeQuery(sqlSentenc);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.next()){
				ids.add(1, rs.getInt(1));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			ids.add(1, 0);
		}
		sqlSentenc = "SELECT MAX(rec_id) FROM recomendation;";
		try {
			rs = ConnectionService.getConnection().createStatement().executeQuery(sqlSentenc);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.next()){
				ids.add(2, rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ids.add(2, 0);
		}
		
		return ids;
	}

	public static String insertProcessRule(AnmFile anm, DrlFile drl, int id_process) throws SQLException{
		try{
			String sqlSentenc = generateAnmSentence(anm);
			sqlSentenc = generateDrlSentence(drl, sqlSentenc);
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			int id = generateAnmStatement(anm, 1, cs, id_process);
			generateDrlStatement(drl, id, cs, id_process);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}

	public static String generateAnmSentence(AnmFile anm){
		String sqlSentenc = "";
		ArrayList<Variable> variables = anm.getVariables();
		ArrayList<Cause> causes = anm.getCauses();
		ArrayList<Recomendation> recomendations = anm.getRecomendations();

		//variables
		for(int i=0; i<variables.size(); i++){
			sqlSentenc = sqlSentenc + "INSERT INTO variable VALUES(?,?,?,?,?,?); ";
		}
		//causas
		for(int i=0; i<causes.size(); i++){
			sqlSentenc = sqlSentenc + "INSERT INTO cause VALUES(?,?,?); ";
		}
		//recomendaciones
		for(int i=0; i<recomendations.size(); i++){
			sqlSentenc = sqlSentenc + "INSERT INTO recomendation VALUES(?,?,?); ";
		}

		return sqlSentenc;
	}

	public static String generateDrlSentence(DrlFile drl, String sqlSentenc){
		ArrayList<VariableCause> varCause = drl.getVarCause();
		ArrayList<CauseRecomendation> causesRec = drl.getCauseRec();

		//variable-causa
		for(int i=0; i<varCause.size(); i++){
			sqlSentenc = sqlSentenc + "INSERT INTO var_cause VALUES(DEFAULT,?,?,?,?); ";
		}
		//causa-recomendacion
		for(int i=0; i<causesRec.size(); i++){
			sqlSentenc = sqlSentenc + "INSERT INTO cause_recomendation VALUES(DEFAULT,?,?,?); ";
		}

		return sqlSentenc;
	}

	public static int generateAnmStatement(AnmFile anm, int id, CallableStatement cs, int id_process) throws SQLException{
		ArrayList<Variable> variables = anm.getVariables();
		ArrayList<Cause> causes = anm.getCauses();
		ArrayList<Recomendation> recomendations = anm.getRecomendations();

		//variables
		for(int i=0; i<variables.size(); i++){
			cs.setInt(id, variables.get(i).getVar_id());
			id = id + 1;
			cs.setInt(id, id_process);
			id = id + 1;
			cs.setString(id, variables.get(i).getVar_name());
			id = id + 1;
			cs.setString(id, variables.get(i).getVar_type());
			id = id + 1;
			cs.setFloat(id, variables.get(i).getMin_value());
			id = id + 1;
			cs.setFloat(id, variables.get(i).getMax_value());
			id = id + 1;
		}
		//causas
		for(int i=0; i<causes.size(); i++){
			cs.setInt(id, causes.get(i).getCause_id());
			id = id + 1;
			cs.setInt(id, id_process);
			id = id + 1;
			cs.setString(id, causes.get(i).getCause_name());
			id = id + 1;
		}
		//causas
		for(int i=0; i<recomendations.size(); i++){
			cs.setInt(id, recomendations.get(i).getRec_id());
			id = id + 1;
			cs.setInt(id, id_process);
			id = id + 1;
			cs.setString(id, recomendations.get(i).getRec_name());
			id = id + 1;
		}

		return id;
	}

	public static void generateDrlStatement(DrlFile drl, int id, CallableStatement cs, int id_process) throws SQLException{
		ArrayList<VariableCause> varCause = drl.getVarCause();
		ArrayList<CauseRecomendation> causesRec = drl.getCauseRec();

		//variable-causa
		for(int i=0; i<varCause.size(); i++){
			cs.setInt(id, id_process);
			id = id + 1;
			cs.setInt(id, varCause.get(i).getVar_id());
			id = id + 1;
			cs.setString(id, varCause.get(i).getState_var());
			id = id + 1;
			cs.setInt(id, varCause.get(i).getCause_id());
			id = id + 1;
		}
		//causa-recomendacion
		for(int i=0; i<causesRec.size(); i++){
			cs.setInt(id, causesRec.get(i).getCause_id());
			id = id + 1;
			cs.setInt(id, causesRec.get(i).getRec_id());
			id = id + 1;
			cs.setInt(id, id_process);
			id = id + 1;
		}
	}
}
