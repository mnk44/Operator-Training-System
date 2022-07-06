package contentClass;

import java.sql.Timestamp;

public class Process {
	private int id_process;
	private String name_process;
	private Timestamp creation_date;
	private byte[] process_image;
	private byte[] anm_file;
	private byte[] drl_file;
	private int area;
	
	public Process(int id_process, String name_process,
			Timestamp creation_date, byte[] process_image, byte[] anm_file,
			byte[] drl_file, int area) {
		super();
		this.id_process = id_process;
		this.name_process = name_process;
		this.creation_date = creation_date;
		this.process_image = process_image;
		this.anm_file = anm_file;
		this.drl_file = drl_file;
		this.area = area;
	}
	
	public Process(String name_process,
			Timestamp creation_date, byte[] process_image, byte[] anm_file,
			byte[] drl_file, int area) {
		super();
		this.name_process = name_process;
		this.creation_date = creation_date;
		this.process_image = process_image;
		this.anm_file = anm_file;
		this.drl_file = drl_file;
		this.area = area;
	}

	public int getId_process() {
		return id_process;
	}

	public void setId_process(int id_process) {
		this.id_process = id_process;
	}
	
	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public String getName_process() {
		return name_process;
	}

	public void setName_process(String name_process) {
		this.name_process = name_process;
	}

	public Timestamp getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Timestamp creation_date) {
		this.creation_date = creation_date;
	}

	public byte[] getProcess_image() {
		return process_image;
	}

	public void setProcess_image(byte[] process_image) {
		this.process_image = process_image;
	}

	public byte[] getAnm_file() {
		return anm_file;
	}

	public void setAnm_file(byte[] anm_file) {
		this.anm_file = anm_file;
	}

	public byte[] getDrl_file() {
		return drl_file;
	}

	public void setDrl_file(byte[] drl_file) {
		this.drl_file = drl_file;
	}
}
