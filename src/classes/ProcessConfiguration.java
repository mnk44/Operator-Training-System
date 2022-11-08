package systemClass;

import systemEnums.QuestionsTypes;

public class ProcessConfiguration {
	int class_id;
	int process_id;
	int cant_try;
	int try_aprove;
	boolean consecutive;
	QuestionsTypes var_question;
	QuestionsTypes cause_question;
	QuestionsTypes rec_question;
	
	public ProcessConfiguration(int process_id, int cant_try, int try_aprove,
			boolean consecutive, String var_question,
			String cause_question, String rec_question) {
		super();
		this.process_id = process_id;
		this.cant_try = cant_try;
		this.try_aprove = try_aprove;
		this.consecutive = consecutive;
		this.var_question = QuestionsTypes.valueOf(var_question.replace(" ", "_"));
		this.cause_question = QuestionsTypes.valueOf(cause_question.replace(" ", "_"));
		this.rec_question = QuestionsTypes.valueOf(rec_question.replace(" ", "_"));
	}
	
	public ProcessConfiguration(int class_id, int process_id, int cant_try,
			int try_aprove, boolean consecutive, String var_question,
			String cause_question, String rec_question) {
		super();
		this.class_id = class_id;
		this.process_id = process_id;
		this.cant_try = cant_try;
		this.try_aprove = try_aprove;
		this.consecutive = consecutive;
		this.var_question = QuestionsTypes.valueOf(var_question.replace(" ", "_"));
		this.cause_question = QuestionsTypes.valueOf(cause_question.replace(" ", "_"));
		this.rec_question = QuestionsTypes.valueOf(rec_question.replace(" ", "_"));
	}
	
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}
	public int getCant_try() {
		return cant_try;
	}
	public void setCant_try(int cant_try) {
		this.cant_try = cant_try;
	}
	public int getTry_aprove() {
		return try_aprove;
	}
	public void setTry_aprove(int try_aprove) {
		this.try_aprove = try_aprove;
	}
	public boolean isConsecutive() {
		return consecutive;
	}
	public void setConsecutive(boolean consecutive) {
		this.consecutive = consecutive;
	}
	public QuestionsTypes getVar_question() {
		return var_question;
	}
	public void setVar_question(QuestionsTypes var_question) {
		this.var_question = var_question;
	}
	public QuestionsTypes getCause_question() {
		return cause_question;
	}
	public void setCause_question(QuestionsTypes cause_question) {
		this.cause_question = cause_question;
	}
	public QuestionsTypes getRec_question() {
		return rec_question;
	}
	public void setRec_question(QuestionsTypes rec_question) {
		this.rec_question = rec_question;
	}
}
