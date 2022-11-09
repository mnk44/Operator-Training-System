package classes;

public class Area {
	int id_area;
	String name_area;
	boolean area_empty;
	
	public Area(int id_area, String name_area, boolean area_empty) {
		super();
		this.id_area = id_area;
		this.name_area = name_area;
		this.area_empty = area_empty;
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
	public boolean isArea_empty() {
		return area_empty;
	}
	public void setArea_empty(boolean area_empty) {
		this.area_empty = area_empty;
	}
}
