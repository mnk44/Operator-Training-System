package logic;

import java.sql.Timestamp;

public class Train {
	
	private int id;
	private int operator;
	private float result;
	private float var_result;
	private float cause_result;
	private float rec_result;
	private Timestamp start;
	private Timestamp finish;
	private int process;
	
	public Train(int id, int operator, float result, float var_result,
			float cause_result, float rec_result, Timestamp start,
			Timestamp finish, int process) {
		super();
		this.id = id;
		this.operator = operator;
		this.result = result;
		this.var_result = var_result;
		this.cause_result = cause_result;
		this.rec_result = rec_result;
		this.start = start;
		this.finish = finish;
		this.process = process;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public float getResult() {
		return result;
	}

	public void setResult(float result) {
		this.result = result;
	}

	public float getVar_result() {
		return var_result;
	}

	public void setVar_result(float var_result) {
		this.var_result = var_result;
	}

	public float getCause_result() {
		return cause_result;
	}

	public void setCause_result(float cause_result) {
		this.cause_result = cause_result;
	}

	public float getRec_result() {
		return rec_result;
	}

	public void setRec_result(float rec_result) {
		this.rec_result = rec_result;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getFinish() {
		return finish;
	}

	public void setFinish(Timestamp finish) {
		this.finish = finish;
	}

	public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		this.process = process;
	}
}
