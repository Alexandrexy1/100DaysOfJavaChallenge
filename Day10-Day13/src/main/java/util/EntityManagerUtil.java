package util;

import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {
	private static final Map<String, String> properties = PropertiesUtil.getProperties();
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("testdb", properties);
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public static void close() {;
		emf.close();
	}
}
