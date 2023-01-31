package extras;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import services.ProcessService;
import services.TraceService;
import services.TrainingService;
import services.UserService;
import classes.Area;
import classes.Trace;
import classes.Training;
import classes.TrainingPrepare;
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
		date.addColumn("Áreas",name_area.toArray());
		table.setModel(date);
	}
	
	public static void fillProcess(DefaultTableModel date, JTable table, ArrayList<Process> process) throws SQLException{
		ArrayList<Integer> process_id = new ArrayList<Integer>();
		ArrayList<String> process_name = new ArrayList<String>();
		
		for(int i=0; i<process.size(); i++){
			process_id.add(process.get(i).getProcess_id());
			process_name.add(process.get(i).getProcess_name());
		}

		date = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("ID",process_id.toArray());
		date.addColumn("Nombre",process_name.toArray());
		table.setModel(date);
	}	

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
					rol.add("Jefe de área");
				}
				if(users.get(i).isUser_active()){
					active.add("sí");
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
		date.addColumn("Área",area.toArray());
		date.addColumn("Activo",active.toArray());
		table.setModel(date);
	}
	
	public static void fillPTrain(DefaultTableModel date, JTable table, TrainingPrepare train) throws SQLException{
		ArrayList<String> state = new ArrayList<>();

		for(int i=0; i<train.getConfigurationList().size(); i++){
			state.add(stateProcess(train.getConfigurationList().get(i).getProcess_id(), train.getTraining()));
		}

		date = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("Proceso",train.getProcess().toArray());
		date.addColumn("Estado",state.toArray());
		table.setModel(date);
	}
	
	public static void fillTraces(DefaultTableModel date, JTable table) throws SQLException{
		ArrayList<Trace> t = TraceService.getTraces();
		ArrayList<Integer> id = new ArrayList<Integer>();
		ArrayList<String> action = new ArrayList<String>();
		ArrayList<String> d = new ArrayList<String>();

		for(int i=0; i<t.size(); i++){
			id.add(t.get(i).getTrace_id());
			action.add("El usuario <" + t.get(i).getUser_nick() + "> " + t.get(i).getTrace_accion() + " " + t.get(i).getChange_class());
			d.add(t.get(i).getTrace_date().toString().substring(0, 10));
		}

		date = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("ID",id.toArray());
		date.addColumn("Acción",action.toArray());
		date.addColumn("Fecha",d.toArray());
		table.setModel(date);
	}
	
	public static void fillTracesOp(DefaultTableModel date, JTable table, int area) throws SQLException{
		ArrayList<Training> t = TrainingService.getTrains(area);
		ArrayList<String> proceso = new ArrayList<String>();
		ArrayList<String> operator = new ArrayList<String>();
		ArrayList<String> state = new ArrayList<String>();
		ArrayList<String> note = new ArrayList<String>();
		String aux;
		String n;

		for(int i=0; i<t.size(); i++){
			proceso.add(ProcessService.searchId(t.get(i).getProcess_id()).getProcess_name());
			operator.add(((User)UserService.searchId(t.get(i).getOperator())).getUser_nick());
			if(t.get(i).getVar_note() == -1){
				aux = "No iniciado";
				n = "-";
			}else if(t.get(i).getRec_note() == -1){
				aux = "Iniciado";
				n = "-";
			}else{
				aux = "Terminado";
				double x = t.get(i).getGeneral_note();
				DecimalFormat format = new DecimalFormat("#.00");
				n = format.format(x);
			}
			
			state.add(aux);
			note.add(n);
		}

		date = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("Proceso",proceso.toArray());
		date.addColumn("Operario",operator.toArray());
		date.addColumn("Estado",state.toArray());
		date.addColumn("Nota",note.toArray());
		table.setModel(date);
	}
	
	public static void fillNotes(DefaultTableModel date, JTable table, ArrayList<Process> processList, ArrayList<User> users) throws SQLException{
		ArrayList<String> process = new ArrayList<String>();
		ArrayList<String> nick = new ArrayList<>();
		ArrayList<String> state = new ArrayList<String>();
		ArrayList<String> note = new ArrayList<String>();

		for(int i=0; i<processList.size(); i++){
			process.add(processList.get(i).getProcess_name());
			
		}

		date = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("Proceso",process.toArray());
		date.addColumn("Usuario",nick.toArray());
		date.addColumn("Estado",state.toArray());
		date.addColumn("Nota",note.toArray());
		table.setModel(date);
	}
	
	private static String stateProcess(int process, ArrayList<Training> train){
		String state = "No iniciado";
		int cont = 0;
		
		while(cont < train.size() &&  state.equals("No iniciado")){
			if(train.get(cont).getProcess_id() == process){
				if(train.get(cont).getGeneral_note() != -1){
					state = "Terminado";
				}else{
					state = "Iniciado";
				}
			}
			cont++;
		}
		
		return state;
	}
}
