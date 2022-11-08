package systemClass;

import systemEnums.RolesTypes;
import systemEnums.SchoolarLevel;

public class User {
	int user_id;
	String user_name;
	String user_card;
	SchoolarLevel user_academic;
	int user_experience;
	int user_position_years;
	String user_nick;
	String user_password;
	boolean user_active;
	int user_area;
	RolesTypes user_rol;
	
	public User(int user_id, String user_name, String user_card,
			SchoolarLevel user_academic, int user_experience,
			int user_position_years, String user_nick, String user_password,
			boolean user_active, int user_area, RolesTypes user_rol) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_card = user_card;
		this.user_academic = user_academic;
		this.user_experience = user_experience;
		this.user_position_years = user_position_years;
		this.user_nick = user_nick;
		this.user_password = user_password;
		this.user_active = user_active;
		this.user_area = user_area;
		this.user_rol = user_rol;
	}
	
	public User(String user_name, String user_card,
			SchoolarLevel user_academic, int user_experience,
			int user_position_years, String user_nick, String user_password,
			boolean user_active, int user_area, RolesTypes user_rol) {
		super();
		this.user_name = user_name;
		this.user_card = user_card;
		this.user_academic = user_academic;
		this.user_experience = user_experience;
		this.user_position_years = user_position_years;
		this.user_nick = user_nick;
		this.user_password = user_password;
		this.user_active = user_active;
		this.user_area = user_area;
		this.user_rol = user_rol;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_card() {
		return user_card;
	}

	public void setUser_card(String user_card) {
		this.user_card = user_card;
	}

	public SchoolarLevel getUser_academic() {
		return user_academic;
	}

	public void setUser_academic(SchoolarLevel user_academic) {
		this.user_academic = user_academic;
	}

	public int getUser_experience() {
		return user_experience;
	}

	public void setUser_experience(int user_experience) {
		this.user_experience = user_experience;
	}

	public int getUser_position_years() {
		return user_position_years;
	}

	public void setUser_position_years(int user_position_years) {
		this.user_position_years = user_position_years;
	}

	public String getUser_nick() {
		return user_nick;
	}

	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public boolean isUser_active() {
		return user_active;
	}

	public void setUser_active(boolean user_active) {
		this.user_active = user_active;
	}

	public int getUser_area() {
		return user_area;
	}

	public void setUser_area(int user_area) {
		this.user_area = user_area;
	}

	public RolesTypes getUser_rol() {
		return user_rol;
	}

	public void setUser_rol(RolesTypes user_rol) {
		this.user_rol = user_rol;
	}
}
