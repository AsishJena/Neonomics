package WebUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	
	public Properties getCredentials() {
		File file = new File("application.properties");
		  
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
			System.out.println("Properties files read successfully. " + prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	
	}
}
