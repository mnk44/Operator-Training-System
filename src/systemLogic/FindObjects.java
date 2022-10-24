package systemLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import systemClass.Area;
import systemClass.User;

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
	
	public static ArrayList<User> findUsers (ArrayList<User> user, String name) throws SQLException{
		ArrayList<User> result = new ArrayList<>();
		
		for(int i=0; i<user.size(); i++){
			if(user.get(i).getUser_name().toLowerCase().contains(name.toLowerCase())){
				result.add(user.get(i));
			}else if(user.get(i).getUser_nick().toLowerCase().contains(name.toLowerCase())){
				result.add(user.get(i));
			}
		}
		
		return result;
	}
}
