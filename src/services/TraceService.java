package systemServices;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import systemClass.Trace;
import systemEnums.AccionTrace;
import systemEnums.SystemClass;

public class TraceService {
	
	public static String newTrace(int trace_user, AccionTrace trace_accion,
			SystemClass trace_class, String trace_class_id, Timestamp trace_date) throws SQLException{
		try{
			String sqlSentenc = "INSERT INTO system_trace VALUES (DEFAULT,?,?,?,?,?)";
			CallableStatement cs = ConnectionService.getConnection().prepareCall(sqlSentenc);
			cs.setInt(1, trace_user);
			cs.setString(2, trace_accion.toString());
			cs.setString(3, trace_class.toString());
			cs.setString(4, trace_class_id);
			cs.setTimestamp(5, trace_date);
			cs.execute();
			cs.close();
		}catch(Exception e){
			return e.getMessage();
		}
		return null;
	}
	
	public static Object getTraces() throws SQLException{
		ArrayList<Trace> tracesList = new ArrayList<Trace>();
		try{
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM system_trace");
			while(rs.next()){				
				Trace trace = new Trace(rs.getInt("trace_id"),rs.getInt("trace_user"),AccionTrace.valueOf(rs.getString("trace_accion")),
						SystemClass.valueOf(rs.getString("trace_class")),rs.getString("trace_class_id"),rs.getTimestamp("trace_date"));
				tracesList.add(trace);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return tracesList;
	}
}
