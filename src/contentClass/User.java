package contentClass;

import utils.Rol;
import utils.SchoolLevel;

public class User {
	private int id_user;
	private String name_user;
	private String identity_card;  //carnet de identidad
	private SchoolLevel school_level;  //nivel escolar
	private int experience;      //años de experiencia en la fábrica
	private int position_years; //años de experiencia en el cargo de jefe de área
	private String nick_name;  //es el nombre de usuario del sistema
	private String password;
	private int area;
	private Rol rol;
	private boolean sleep;
	
	public User(String name_user, String identity_card,
			SchoolLevel school_level, int experience, int position_years,
			String nick_name, String password, int area, Rol rol, boolean sleep) {
		super();
		this.name_user = name_user;
		this.identity_card = identity_card;
		this.school_level = school_level;
		this.experience = experience;
		this.position_years = position_years;
		this.nick_name = nick_name;
		this.password = password;
		this.area = area;
		this.rol = rol;
		this.sleep = sleep;
	}
	
	public User(int id_user, String name_user, String identity_card,
			SchoolLevel school_level, int experience, int position_years,
			String nick_name, String password, int area, Rol rol, boolean sleep) {
		super();
		this.id_user = id_user;
		this.name_user = name_user;
		this.identity_card = identity_card;
		this.school_level = school_level;
		this.experience = experience;
		this.position_years = position_years;
		this.nick_name = nick_name;
		this.password = password;
		this.area = area;
		this.rol = rol;
		this.sleep = sleep;
	}

	public boolean getSleep() {
		return sleep;
	}

	public void setSleep(boolean sleep) {
		this.sleep = sleep;
	}
	
	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getName_user() {
		return name_user;
	}

	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

	public String getIdentity_card() {
		return identity_card;
	}

	public void setIdentity_card(String identity_card) {
		this.identity_card = identity_card;
	}

	public SchoolLevel getSchool_level() {
		return school_level;
	}

	public void setSchool_level(SchoolLevel school_level) {
		this.school_level = school_level;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getPosition_years() {
		return position_years;
	}

	public void setPosition_years(int position_years) {
		this.position_years = position_years;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
