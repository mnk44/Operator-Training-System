package assistants;

import java.sql.SQLException;
import java.util.ArrayList;

import classes.Area;
import classes.FactoryProcess;
import classes.Trace;
import classes.User;
import services.UserService;

public class FindObjects {

	public static ArrayList<Area> findArea (ArrayList<Area> areas, String name) throws SQLException{
		ArrayList<Area> result = new ArrayList<>();
		
		for(int i=0; i<areas.size(); i++){
			String areaName = areas.get(i).getArea_name().toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
			String findingName = name.toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
			if(areaName.contains(findingName)){
				result.add(areas.get(i));
			}
		}
		
		return result;
	}
	
	public static ArrayList<Trace> findTrace (ArrayList<Trace> traces, String name) throws SQLException{
		ArrayList<Trace> result = new ArrayList<>();
		
		for(int i=0; i<traces.size(); i++){
			String userName = ((User)UserService.findId(traces.get(i).getTrace_user())).getUser_nick().toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
			name = name.toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
			if(userName.contains(name)){
				result.add(traces.get(i));
			}
		}
		
		return result;
	}
	
	public static ArrayList<FactoryProcess> findProcess (ArrayList<FactoryProcess> process, String name) throws SQLException{
		ArrayList<FactoryProcess> result = new ArrayList<>();
		
		for(int i=0; i<process.size(); i++){
			String procesName = process.get(i).getProcess_name().toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
			String findingName = name.toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
			if(procesName.contains(findingName)){
				result.add(process.get(i));
			}
		}
		
		return result;
	}
	
	public static ArrayList<User> findUsers (ArrayList<User> user, String name) throws SQLException{
		ArrayList<User> result = new ArrayList<>();
		
		for(int i=0; i<user.size(); i++){
			String userName = user.get(i).getUser_name().toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
			String nickName = user.get(i).getUser_nick().toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
			String findingName = name.toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
			if(userName.contains(findingName)){
				result.add(user.get(i));
			}else if(nickName.contains(findingName)){
				result.add(user.get(i));
			}else if(user.get(i).getUser_card().contains(findingName)){
				result.add(user.get(i));
			}
		}
		
		return result;
	}
}
