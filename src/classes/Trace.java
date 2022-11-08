package classes;

import java.sql.Timestamp;

import enums.AccionTrace;
import enums.SystemClass;

public class Trace {
	int trace_id;
	int trace_user;
	AccionTrace trace_accion;
	SystemClass trace_class;
	String trace_class_id;
	Timestamp trace_date;
	
	public Trace(int trace_id, int trace_user, AccionTrace trace_accion,
			SystemClass trace_class, String trace_class_id, Timestamp trace_date) {
		super();
		this.trace_id = trace_id;
		this.trace_user = trace_user;
		this.trace_accion = trace_accion;
		this.trace_class = trace_class;
		this.trace_class_id = trace_class_id;
		this.trace_date = trace_date;
	}
	
	public Trace(int trace_user, AccionTrace trace_accion,
			SystemClass trace_class, String trace_class_id, Timestamp trace_date) {
		super();
		this.trace_user = trace_user;
		this.trace_accion = trace_accion;
		this.trace_class = trace_class;
		this.trace_class_id = trace_class_id;
		this.trace_date = trace_date;
	}
	
	public int getTrace_id() {
		return trace_id;
	}
	public void setTrace_id(int trace_id) {
		this.trace_id = trace_id;
	}
	public int getTrace_user() {
		return trace_user;
	}
	public void setTrace_user(int trace_user) {
		this.trace_user = trace_user;
	}
	public AccionTrace getTrace_accion() {
		return trace_accion;
	}
	public void setTrace_accion(AccionTrace trace_accion) {
		this.trace_accion = trace_accion;
	}
	public SystemClass getTrace_class() {
		return trace_class;
	}
	public void setTrace_class(SystemClass trace_class) {
		this.trace_class = trace_class;
	}
	public String getTrace_class_id() {
		return trace_class_id;
	}
	public void setTrace_class_id(String trace_class_id) {
		this.trace_class_id = trace_class_id;
	}
	public Timestamp getTrace_date() {
		return trace_date;
	}
	public void setTrace_date(Timestamp trace_date) {
		this.trace_date = trace_date;
	}
}
