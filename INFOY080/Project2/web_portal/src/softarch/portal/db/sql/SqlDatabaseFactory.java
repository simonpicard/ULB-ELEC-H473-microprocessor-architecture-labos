package softarch.portal.db.sql;

import java.util.Properties;

import softarch.portal.db.DatabaseFactory;
import softarch.portal.db.RawDatabase;
import softarch.portal.db.RegularDatabase;
import softarch.portal.db.UserDatabase;

public class SqlDatabaseFactory extends DatabaseFactory {
	
	private String dbUser;
	private String dbPassword;
	private String dbUrl;
	
	public SqlDatabaseFactory(Properties properties) {
		this.dbUser = properties.getProperty("dbUser");
		this.dbPassword = properties.getProperty("dbPassword");
		this.dbUrl = properties.getProperty("dbUrl");
	}

	@Override
	public UserDatabase createUserDatabase() {
		return new SqlUserDatabase(dbUser, dbPassword, dbUrl);
	}

	@Override
	public RegularDatabase createRegularDatabase() {
		return new SqlRegularDatabase(dbUser, dbPassword, dbUrl);
	}

	@Override
	public RawDatabase createRawDatabase() {
		return new SqlRawDatabase(dbUser, dbPassword, dbUrl);
	}

}
