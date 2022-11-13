package extras;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classes.Area;
import classes.User;
import classes.Process;

public class DataTable {
	
	public static void fillArea(DefaultTableModel date, JTable table, ArrayList<Area> areas) throws SQLException{
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

		date.addColumn("ID",id_area.toArray());
		date.addColumn("�reas",name_area.toArray());
		table.setModel(date);
	}
	
	public static void fillProcess(DefaultTableModel date, JTable table, ArrayList<Process> process, ArrayList<Area> areas) throws SQLException{
		ArrayList<Integer> process_id = new ArrayList<Integer>();
		ArrayList<String> process_name = new ArrayList<String>();
		ArrayList<String> area_name = new ArrayList<String>();
		
		for(int i=0; i<process.size(); i++){
			process_id.add(process.get(i).getProcess_id());
			process_name.add(process.get(i).getProcess_name());
			boolean a = true;
			for(int j=0; j<areas.size() && a;j++){
				if(areas.get(j).getId_area() == process.get(i).getProcess_area()){
					area_name.add(areas.get(j).getName_area());
					a = false;
				}
			}
		}

		date = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("ID",process_id.toArray());
		date.addColumn("Nombre",process_name.toArray());
		date.addColumn("�rea",area_name.toArray());
		table.setModel(date);
	}
//	
//	@SuppressWarnings("deprecation")
//	public static void getTraces(DefaultTableModel date, JTable table, ArrayList<Trace> traces, String fecha) throws SQLException{
//		ArrayList<String> traceResp = new ArrayList<String>();
//		ArrayList<String> accion = new ArrayList<String>();
//		ArrayList<String> hora = new ArrayList<String>();
//		
//		for(int i=0; i<traces.size(); i++){
//			if(traces.get(i).getTrace_date().toGMTString().substring(0, 11).equals(fecha)){
//				traceResp.add(((User)UserService.findId(traces.get(i).getTrace_user())).getUser_nick());
//				String act = traces.get(i).getTrace_accion().toString().replace("_", " ");
//				accion.add(act.substring(0,1).toUpperCase() + act.substring(1).toLowerCase() + " " + traces.get(i).getTrace_class().toString() +
//						" <" + traces.get(i).getTrace_class_id() + ">");
//				hora.add(traces.get(i).getTrace_date().getHours() + ":" + traces.get(i).getTrace_date().getMinutes() + ":" + traces.get(i).getTrace_date().getSeconds());
//			}
//		}
//
//		date = new DefaultTableModel(){
//			private static final long serialVersionUID = 1L;
//			public boolean isCellEditable(int r,int c){
//				return false;
//			}
//		};
//
//		date.addColumn("Usuario",traceResp.toArray());
//		date.addColumn("Acci�n",accion.toArray());
//		date.addColumn("Hora",hora.toArray());
//		table.setModel(date);
//	}
//	
//	@SuppressWarnings("unchecked")
//	public static void getProcessAction(DefaultTableModel date, JTable table) throws SQLException{
//		ArrayList<FactoryProcess> process = (ArrayList<FactoryProcess>) FactoryProcessService.getProcess();
//		ArrayList<Integer> process_id = new ArrayList<Integer>();
//		ArrayList<String> process_name = new ArrayList<String>();
//		ArrayList<String> area_name = new ArrayList<String>();
//		ArrayList<ImageIcon> edit = new ArrayList<ImageIcon>();
//		
//		for(int i=0; i<process.size(); i++){
//			process_id.add(process.get(i).getProcess_id());
//			process_name.add(process.get(i).getProcess_name());
//			Area area = (Area) AreaService.findId(process.get(i).getProcess_area());
//			area_name.add(area.getArea_name());
//			edit.add(new ImageIcon(DataTable.class.getResource("/imgs/tableEdit.png")));
//		}
//
//		date = new DefaultTableModel(){
//			private static final long serialVersionUID = 1L;
//			public boolean isCellEditable(int r,int c){
//				return false;
//			}
//		};
//
//		date.addColumn("ID",process_id.toArray());
//		date.addColumn("Nombre",process_name.toArray());
//		date.addColumn("�rea",area_name.toArray());
//		date.addColumn(" ",area_name.toArray());
//		table.setModel(date);
//	}
//	
	public static void fillUsers(DefaultTableModel date, JTable table, ArrayList<User> users, ArrayList<Area> areas, int user_id) throws SQLException{
		ArrayList<Integer> id_user = new ArrayList<Integer>();
		ArrayList<String> nick = new ArrayList<String>();
		ArrayList<String> rol = new ArrayList<String>();
		ArrayList<String> area = new ArrayList<String>();
		ArrayList<String> active = new ArrayList<String>();

		for(int i=0; i<users.size(); i++){
			if(user_id != users.get(i).getUser_id()){
				id_user.add(users.get(i).getUser_id());
				nick.add(users.get(i).getUser_nick());
				boolean a = true;
				for(int j=0; j<areas.size() && a;j++){
					if(areas.get(j).getId_area() == users.get(i).getUser_area()){
						area.add(areas.get(j).getName_area());
						a = false;
					}
				}
				if(users.get(i).getUser_rol() == 0){
					rol.add("Administrador");
				}else if(users.get(i).getUser_rol() == 3){
					rol.add("Operario");
				}else{
					rol.add("Jefe de �rea");
				}
				if(users.get(i).isUser_active()){
					active.add("s�");
				}else{
					active.add("no");
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
		date.addColumn("Usuario",nick.toArray());
		date.addColumn("Rol",rol.toArray());
		date.addColumn("�rea",area.toArray());
		date.addColumn("Activo",active.toArray());
		table.setModel(date);
	}
}
