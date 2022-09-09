package systemClass;

public class FactoryProcess {
	int process_id;
	String process_name;
	int process_area;
	byte[] process_img;
	byte[] process_anm;
	byte[] process_drl;
	
	public FactoryProcess(int process_id, String process_name,
			int process_area, byte[] process_img,
			byte[] process_anm, byte[] process_drl) {
		super();
		this.process_id = process_id;
		this.process_name = process_name;
		this.process_area = process_area;
		this.process_img = process_img;
		this.process_anm = process_anm;
		this.process_drl = process_drl;
	}
	
	public FactoryProcess(String process_name, int process_area,
		    byte[] process_img, byte[] process_anm,
			byte[] process_drl) {
		super();
		this.process_name = process_name;
		this.process_area = process_area;
		this.process_img = process_img;
		this.process_anm = process_anm;
		this.process_drl = process_drl;
	}
	
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}
	public String getProcess_name() {
		return process_name;
	}
	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}
	public int getProcess_area() {
		return process_area;
	}
	public void setProcess_area(int process_area) {
		this.process_area = process_area;
	}
	public byte[] getProcess_img() {
		return process_img;
	}
	public void setProcess_img(byte[] process_img) {
		this.process_img = process_img;
	}
	public byte[] getProcess_anm() {
		return process_anm;
	}
	public void setProcess_anm(byte[] process_anm) {
		this.process_anm = process_anm;
	}
	public byte[] getProcess_drl() {
		return process_drl;
	}
	public void setProcess_drl(byte[] process_drl) {
		this.process_drl = process_drl;
	}
}
