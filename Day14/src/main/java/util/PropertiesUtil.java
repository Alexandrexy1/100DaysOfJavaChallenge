package util;

import java.util.HashMap;
import java.util.Map;

public class PropertiesUtil {
	public static Map<String, String> getProperties() {
		Map<String, String> properties = new HashMap<>();
	
		String jdbcUrl = System.getenv("jdbc_url");
		String jdbcUsername = System.getenv("jdbc_user");
		String jdbcPassword = System.getenv("jdbc_password");
	
		properties.put("jakarta.persistence.jdbc.url", jdbcUrl);
		properties.put("jakarta.persistence.jdbc.user", jdbcUsername);
		properties.put("jakarta.persistence.jdbc.password", jdbcPassword);
	
		return properties;
	}
}
