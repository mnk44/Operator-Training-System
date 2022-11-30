package extras;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Convert {

	public static byte[] toBytes(Object object) throws IOException{ 
		if(object != null){
			File f = new File((String) object);
			ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
			ObjectOutputStream oos = new ObjectOutputStream(baos); 
			oos.writeObject(f); 

			return baos.toByteArray(); 
		}
		return null;
	} 

	public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException{ 
		Object object = null; 
		object = new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject(); 

		return object; 
	}
}
