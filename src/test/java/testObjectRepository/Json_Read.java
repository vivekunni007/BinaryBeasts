package testObjectRepository;

/**
 * This class is defined in order to read data values from JSON file.
 * 
 * @author BINARYBEASTS
 * @since 2020/11/27
 * 
 */

import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Json_Read {
	static    String name;
	public static String readFromJson(String key)
	{
		JSONParser parser = new JSONParser();
	      try {
	         Object obj = parser.parse(new FileReader("./src/test/resources/Locators.json"));
	         JSONObject jsonObject = (JSONObject)obj;
	         name = (String)jsonObject.get(key);
				
	} catch(Exception e) {
        e.printStackTrace();
     }
	      return name;
	
}
}