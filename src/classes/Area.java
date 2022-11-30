package classes;

public class Area {
	int id_area;
	String name_area;

	public Area(int id_area, String name_area) {
		super();
		this.id_area = id_area;
		this.name_area = name_area;
	}
	
	public int getId_area() {
		return id_area;
	}
	public void setId_area(int id_area) {
		this.id_area = id_area;
	}
	public String getName_area() {
		return name_area;
	}
	public void setName_area(String name_area) {
		this.name_area = name_area;
	}
}
