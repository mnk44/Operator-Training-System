package logic;

import java.sql.SQLException;

import contentClass.User;
import services.UserService;

public class CheckLogin {
	public static boolean noEmpty(String user, String pass){
		if(user.isEmpty()){
			return false;
		}else if(pass.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
	public static User checkPass(String user, String pass) throws SQLException{
		User uss = UserService.findByNick(user);
		if(uss != null && Encrypting.getMd5(pass).equals(uss.getPassword())){
			return uss;
		}else{
			System.out.println("no Devolvi el usuario");
			return null;
		}
	}
}
