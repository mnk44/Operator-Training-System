package classes;

import java.io.UnsupportedEncodingException;

import extras.Encrypting;

public class User {
	int user_id;
	String user_name;
	int user_sex;
	String user_card;
	int user_experience;
	String user_level;
	int user_boss;
	String user_nick;
	String user_pass;
	boolean user_active;
	int user_area;
	int user_rol;
	
	public User(int user_id, String user_name, int user_sex, String user_card,
			int user_experience, String user_level, int user_boss,
			String user_nick, String user_pass, boolean user_active,
			int user_area, int user_rol) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_sex = user_sex;
		this.user_card = user_card;
		this.user_experience = user_experience;
		this.user_level = user_level;
		this.user_boss = user_boss;
		this.user_nick = user_nick;
		this.user_pass = user_pass;
		this.user_active = user_active;
		this.user_area = user_area;
		this.user_rol = user_rol;
	}
	
	public User(String user_name, int user_sex, String user_card,
			int user_experience, String user_level, int user_boss,
			String user_nick, String user_pass, boolean user_active,
			int user_area, int user_rol) throws UnsupportedEncodingException {
		super();
		this.user_name = user_name;
		this.user_sex = user_sex;
		this.user_card = user_card;
		this.user_experience = user_experience;
		this.user_level = user_level;
		this.user_boss = user_boss;
		this.user_nick = user_nick;
		this.user_pass = Encrypting.getEncript(user_pass);
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
	public int getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(int user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_card() {
		return user_card;
	}
	public void setUser_card(String user_card) {
		this.user_card = user_card;
	}
	public int getUser_experience() {
		return user_experience;
	}
	public void setUser_experience(int user_experience) {
		this.user_experience = user_experience;
	}
	public String getUser_level() {
		return user_level;
	}
	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}
	public int getUser_boss() {
		return user_boss;
	}
	public void setUser_boss(int user_boss) {
		this.user_boss = user_boss;
	}
	public String getUser_nick() {
		return user_nick;
	}
	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
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
	public int getUser_rol() {
		return user_rol;
	}
	public void setUser_rol(int user_rol) {
		this.user_rol = user_rol;
	}
}
