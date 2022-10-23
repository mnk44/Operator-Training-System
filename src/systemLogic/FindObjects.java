package systemLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import systemClass.Area;

public class FindObjects {

	public static ArrayList<Area> findArea (ArrayList<Area> areas, String name) throws SQLException{
		ArrayList<Area> result = new ArrayList<>();
		
		for(int i=0; i<areas.size(); i++){
			if(areas.get(i).getArea_name().toLowerCase().contains(name.toLowerCase())){
				result.add(areas.get(i));
			}
		}
		
		return result;
	}
}
