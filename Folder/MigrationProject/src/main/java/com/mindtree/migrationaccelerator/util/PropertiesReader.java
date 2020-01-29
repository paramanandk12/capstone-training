package com.mindtree.migrationaccelerator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertiesReader {

	private static Properties prop;

	private PropertiesReader() {

	}

	public static Properties getPropertiesReaderInstance() throws IOException {

		if (prop == null) {
			prop = new Properties();
			ClassLoader classLoader = new PropertiesReader().getClass().getClassLoader();
			URL url = classLoader.getResource("mastersheet_column.properties");
			File file = new File(url.getFile());
			prop.load(new FileInputStream(file));
		}
		return prop;
	}
}