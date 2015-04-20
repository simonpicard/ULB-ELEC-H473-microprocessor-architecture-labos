package softarch.portal.db.json;

import java.util.Properties;

import softarch.portal.db.DatabaseFactory;
import softarch.portal.db.RawDatabase;
import softarch.portal.db.RegularDatabase;
import softarch.portal.db.UserDatabase;

public class JsonDatabaseFactory extends DatabaseFactory {
	
	private String dbUrl;
	
	public JsonDatabaseFactory(Properties properties) {
		this.dbUrl = properties.getProperty("dbUrl");
	}

	@Override
	public UserDatabase createUserDatabase() {
		return new JsonUserDatabase(dbUrl);
	}

	@Override
	public RegularDatabase createRegularDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RawDatabase createRawDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

}
