package configfile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class ConfigFileReader {

	private Properties properties;
	private String propertyFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\config\\configuration.properties";

	public ConfigFileReader() throws IOException {

		try {
			FileInputStream fis = new FileInputStream(propertyFilePath);
			properties = new Properties();
			properties.load(fis);
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.getStackTrace();
			
		}
		
	}
	public String getPropertyByKey(String key) {
		String value=properties.get(key).toString();
		if(StringUtils.isEmpty(value)) {
			throw new RuntimeException("Value is not specified for key in properties file");
		}
		return value;
		
	}
	
}
