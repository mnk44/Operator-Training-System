package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import services.AreaService;
import contentClass.Area;

/**
 * Esta clase llena los contenidos de las tablas
 * 
 * @author HP
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
}
