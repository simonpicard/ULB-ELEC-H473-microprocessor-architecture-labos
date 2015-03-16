package softarch.portal.db;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;

import softarch.portal.db.json.JsonDatabaseFactory;
import softarch.portal.db.sql.SqlDatabaseFactory;

public abstract class DatabaseFactory {
	
	private static HashMap<String, Class> registeredDatabaseType = null;
	
	static {
		registeredDatabaseType = new HashMap<String, Class>();
		populateFactory();
	}
	
	public abstract UserDatabase createUserDatabase();
	
	public abstract RegularDatabase createRegularDatabase();
	
	public abstract RawDatabase createRawDatabase();
	
	public static DatabaseFactory getFactory(String dbUser, String dbPassword, String dbUrl, String dbType) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> factoryClass = registeredDatabaseType.get(dbType);
        Constructor<?> factoryConstructor = null;
        DatabaseFactory databaseFactory = null;
        factoryConstructor = factoryClass.getDeclaredConstructor(String.class, String.class, String.class);
        databaseFactory = (DatabaseFactory) factoryConstructor.newInstance(dbUser, dbPassword, dbUrl);
        return databaseFactory;
	}
	
	private static void populateFactory() {
		registerDatabaseType("SQL", SqlDatabaseFactory.class);
		registerDatabaseType("JSON", JsonDatabaseFactory.class);
	}
	
	private static void registerDatabaseType(String id, Class databaseType) {
		registeredDatabaseType.put(id, databaseType);
	}
}
