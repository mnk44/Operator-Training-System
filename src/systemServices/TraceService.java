package systemServices;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import systemClass.Trace;
import systemEnums.AccionTrace;
import systemEnums.SystemClass;

public class TraceService {
	
	public static String newUser(Trace trace) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO trace VALUES (DEFAULT,?,?,?,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, trace.getTrace_user());
			cs.setString(2, trace.getTrace_accion().toString().replace("_" , ""));
			cs.setString(3, trace.getTrace_class().toString().replace("_" , ""));
			cs.setInt(4, trace.getTrace_class_id());
			cs.setTimestamp(5, trace.getTrace_date());
			cs.setString(6, trace.getTrace_pc());
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static Object getTraces() throws SQLException{
		ArrayList<Trace> tracesList = new ArrayList<Trace>();
		try{
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM trace");
			while(rs.next()){				
				Trace trace = new Trace(rs.getInt("trace_id"),rs.getInt("trace_user"),AccionTrace.valueOf(rs.getString("trace_accion")),
						SystemClass.valueOf(rs.getString("trace_class")),rs.getInt("trace_class_id"),rs.getTimestamp("trace_date"),
						rs.getString("trace_pc"));
				
				tracesList.add(trace);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return tracesList;
	}
}
