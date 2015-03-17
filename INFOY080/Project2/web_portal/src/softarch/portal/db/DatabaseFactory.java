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
	
	public static DatabaseFactory getFactory(Properties properties) {
		DatabaseFactory factory = null;
		try {
			factory = getFactory(properties.getProperty("dbType"), properties);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		return factory;
	}
	
	private static DatabaseFactory getFactory(String dbType, Properties properties) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> factoryClass = registeredDatabaseType.get(dbType);
        Constructor<?> factoryConstructor = null;
        DatabaseFactory databaseFactory = null;
        factoryConstructor = factoryClass.getDeclaredConstructor(Properties.class);
        databaseFactory = (DatabaseFactory) factoryConstructor.newInstance(properties);
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
