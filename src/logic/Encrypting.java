package logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * Clase para encriptar las contraseñas de
 * los usuarios
 * 
 * @author Mnk
 *
 */

public class Encrypting {
	
	public static String getMd5(String password){
		MessageDigest md5;
		try {

			md5 = MessageDigest.getInstance("MD5");
			md5.update(password.getBytes());
			byte[] keys = md5.digest();
			return new String(new BASE64Encoder().encode(keys));

		}catch (NoSuchAlgorithmException e){
			return e.getMessage();
		}
	}
}
