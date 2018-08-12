package lib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	private Properties prop = new Properties();
	private String value = null;

	public String getOdlProperties(String key) throws FileNotFoundException {
		InputStream input = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\ObjectDefinitionLibrary.properties");
		if (input != null) {
			try {
				prop.load(input);
				value = prop.getProperty(key);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

//	/public static void main(String[] args) throws FileNotFoundException {
//		System.out.println("we are here");
//		new PropertiesReader().getOdlProperties("name");
//	}
}
