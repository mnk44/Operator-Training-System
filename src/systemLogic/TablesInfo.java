package systemLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import systemClass.Area;
import systemClass.FactoryProcess;
import systemServices.AreaService;
import systemServices.FactoryProcessService;

public class TablesInfo {
	
	@SuppressWarnings("unchecked")
	public static void getProcess(DefaultTableModel date, JTable table) throws SQLException{
		ArrayList<FactoryProcess> process = (ArrayList<FactoryProcess>) FactoryProcessService.getProcess();
		ArrayList<Integer> process_id = new ArrayList<Integer>();
		ArrayList<String> process_name = new ArrayList<String>();
		ArrayList<String> area_name = new ArrayList<String>();
		
		for(int i=0; i<process.size(); i++){
			process_id.add(process.get(i).getProcess_id());
			process_name.add(process.get(i).getProcess_name());
			Area area = (Area) AreaService.findId(process.get(i).getProcess_area());
			area_name.add(area.getArea_name());
		}

		date = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("ID",process_id.toArray());
		date.addColumn("Nombre",process_name.toArray());
		date.addColumn("Área",area_name.toArray());
		table.setModel(date);
	}
	
	@SuppressWarnings("unchecked")
	public static void getProcessAction(DefaultTableModel date, JTable table) throws SQLException{
		ArrayList<FactoryProcess> process = (ArrayList<FactoryProcess>) FactoryProcessService.getProcess();
		ArrayList<Integer> process_id = new ArrayList<Integer>();
		ArrayList<String> process_name = new ArrayList<String>();
		ArrayList<String> area_name = new ArrayList<String>();
		ArrayList<ImageIcon> edit = new ArrayList<ImageIcon>();
		
		for(int i=0; i<process.size(); i++){
			process_id.add(process.get(i).getProcess_id());
			process_name.add(process.get(i).getProcess_name());
			Area area = (Area) AreaService.findId(process.get(i).getProcess_area());
			area_name.add(area.getArea_name());
			edit.add(new ImageIcon(TablesInfo.class.getResource("/imgs/tableEdit.png")));
		}

		date = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};

		date.addColumn("ID",process_id.toArray());
		date.addColumn("Nombre",process_name.toArray());
		date.addColumn("Área",area_name.toArray());
		date.addColumn(" ",area_name.toArray());
		table.setModel(date);
	}
}
