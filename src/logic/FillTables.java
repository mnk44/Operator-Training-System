package logic;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import services.AreaService;
import services.ProcessService;
import services.UserService;
import utils.UserStatus;
import contentClass.Area;
import contentClass.Process;
import contentClass.User;

/**
 * Esta clase llena los contenidos de las tablas
 * 
 * @author Mnk
 *
 */

public class FillTables {

	//Areas
	public static void fillArea(DefaultTableModel date, JTable table) throws SQLException{
		ArrayList<Area> areas = AreaService.getAreas();
		ArrayList<Integer> id_area = new ArrayList<Integer>();
		ArrayList<String> name_area = new ArrayList<String>();

		for(int i=0; i<areas.size(); i++){
			id_area.add(areas.get(i).getId_area());
			name_area.add(areas.get(i).getName_area());
		}

		date = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("Identificadores",id_area.toArray());
		date.addColumn("Áreas",name_area.toArray());
		table.setModel(date);
	}

	//Usuarios
	public static void fillUser(DefaultTableModel date, JTable table, int id_actual_user) throws SQLException{
		ArrayList<User> users = UserService.getUsers();
		ArrayList<Integer> id_user = new ArrayList<Integer>();
		ArrayList<String> nick_name = new ArrayList<String>();
		ArrayList<String> area_name = new ArrayList<String>();
		ArrayList<String> rol = new ArrayList<String>();
		ArrayList<String> sleep = new ArrayList<String>();

		for(int i=0; i<users.size(); i++){
			if(users.get(i).getId_user() != id_actual_user){
				id_user.add(users.get(i).getId_user());
				nick_name.add(users.get(i).getNick_name());
				area_name.add(AreaService.findById(users.get(i).getArea()).getName_area());
				rol.add(users.get(i).getRol().name());
				if(users.get(i).getSleep()){
					sleep.add(UserStatus.INACTIVO.name());
				}else{
					sleep.add(UserStatus.ACTIVO.name());
				}
			}
		}

		date = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("ID",id_user.toArray());
		date.addColumn("Usuario",nick_name.toArray());
		date.addColumn("Área",area_name.toArray());
		date.addColumn("Rol",rol.toArray());
		date.addColumn("Estado",sleep.toArray());
		table.setModel(date);
	}

	//Procesos
	public static void fillProcess(DefaultTableModel date, JTable table) throws SQLException{
		ArrayList<Process> process = ProcessService.getProcess();
		ArrayList<Integer> id_process = new ArrayList<Integer>();
		ArrayList<String> name_process = new ArrayList<String>();
		ArrayList<Timestamp> create_name = new ArrayList<Timestamp>();
		ArrayList<String> area_name = new ArrayList<String>();
		
		for(int i=0; i<process.size(); i++){
			id_process.add(process.get(i).getId_process());
			name_process.add(process.get(i).getName_process());
			create_name.add(process.get(i).getCreation_date());
			area_name.add(AreaService.findById(process.get(i).getArea()).getName_area());
		}

		date = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("ID",id_process.toArray());
		date.addColumn("Nombre",name_process.toArray());
		date.addColumn("Área",area_name.toArray());
		table.setModel(date);
	}
}
