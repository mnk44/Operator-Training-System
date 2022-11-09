package classes;

import java.sql.Timestamp;

public class Trace {
	int trace_id;
	String user_nick;
	String trace_accion;
	String change_class;
	Timestamp trace_date;
	
	public Trace(int trace_id, String user_nick, String trace_accion,
			String change_class, Timestamp trace_date) {
		super();
		this.trace_id = trace_id;
		this.user_nick = user_nick;
		this.trace_accion = trace_accion;
		this.change_class = change_class;
		this.trace_date = trace_date;
	}
	
	public int getTrace_id() {
		return trace_id;
	}
	public void setTrace_id(int trace_id) {
		this.trace_id = trace_id;
	}
	public String getUser_nick() {
		return user_nick;
	}
	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}
	public String getTrace_accion() {
		return trace_accion;
	}
	public void setTrace_accion(String trace_accion) {
		this.trace_accion = trace_accion;
	}
	public String getChange_class() {
		return change_class;
	}
	public void setChange_class(String change_class) {
		this.change_class = change_class;
	}
	public Timestamp getTrace_date() {
		return trace_date;
	}
	public void setTrace_date(Timestamp trace_date) {
		this.trace_date = trace_date;
	}
}
