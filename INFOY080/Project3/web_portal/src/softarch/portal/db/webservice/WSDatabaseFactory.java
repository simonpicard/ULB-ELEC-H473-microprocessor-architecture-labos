package softarch.portal.db.webservice;

import java.util.Properties;

import softarch.portal.db.DatabaseFactory;
import softarch.portal.db.RawDatabase;
import softarch.portal.db.RegularDatabase;
import softarch.portal.db.UserDatabase;

public class WSDatabaseFactory extends DatabaseFactory {

	private String wsLocation;
	
	public WSDatabaseFactory(Properties properties) {
		wsLocation = properties.getProperty("wsLocation");
	}
	
	@Override
	public UserDatabase createUserDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegularDatabase createRegularDatabase() {
		return new WSRegularDatabase(wsLocation);
	}

	@Override
	public RawDatabase createRawDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

}
