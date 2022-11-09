package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Trace;

public class TraceService {
	
	public static Object getTraces() throws SQLException{
		ArrayList<Trace> tracesList = new ArrayList<Trace>();
		try{
			ResultSet rs = ConnectionService.getConnection().createStatement().executeQuery("SELECT * FROM trace");
			while(rs.next()){				
				Trace trace = new Trace(rs.getInt("trace_id"),rs.getString("user_nick"),rs.getString("trace_action"),
						rs.getString("change_class"),rs.getTimestamp("trace_date"));
				tracesList.add(trace);
			}
		}catch (Exception e){
			return e.getMessage();
		}
		return tracesList;
	}
}
