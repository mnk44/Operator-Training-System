package systemLogic;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypting {
	
	public static String getEncript(String password) throws UnsupportedEncodingException{
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-384");
			byte[] digest = md.digest(password.getBytes("UTF-8"));
			return digest.toString();
		}catch (NoSuchAlgorithmException e){
			return e.getMessage();
		}
	}
}
