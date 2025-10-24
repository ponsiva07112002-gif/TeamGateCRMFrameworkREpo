package com.teamgate.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * This class is for performing Properties File related action
 * @author Ponselvi
 */
public class PropertyFileUtility {
	
	/**
	 * This method is used to read common data from Excel 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getDataFromPropertiesFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream("./TestData/TeamgateCRM_commonData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String data = prop.getProperty(key);

		return data;

	}
}
