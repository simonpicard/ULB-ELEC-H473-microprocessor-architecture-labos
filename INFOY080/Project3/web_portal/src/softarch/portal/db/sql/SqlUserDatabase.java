package softarch.portal.db.sql;

import softarch.portal.data.CheapSubscription;
import softarch.portal.data.ExpensiveSubscription;
import softarch.portal.data.ExpertAdministrator;
import softarch.portal.data.ExternalAdministrator;
import softarch.portal.data.FreeSubscription;
import softarch.portal.data.Operator;
import softarch.portal.data.RegularAdministrator;
import softarch.portal.data.UserProfile;
import softarch.portal.db.UserDatabase;
import softarch.portal.db.DatabaseException;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * This class encapsulates the user database.
 * @author Niels Joncheere
 */
public class SqlUserDatabase implements UserDatabase {
	
	protected SqlConnection sqlConnection;
	
	/**
	 * Creates a new user database.
	 */
	public SqlUserDatabase(String dbUser, String dbPassword, String dbUrl) {
		this.sqlConnection = new SqlConnection(dbUser, dbPassword, dbUrl);
	}

	/**
	 * Inserts a new user profile into the user database.
	 */
	public void insert(UserProfile profile)
		throws DatabaseException {
		
		this.sqlConnection.executeSql(profile.asSql());
	}

	/**
	 * Updates an existing user profile in the user database.
	 */
	public void update(UserProfile profile)
		throws DatabaseException {
		
		this.sqlConnection.executeSql(profile.asSqlUpdate());
	}

	/**
	 * Returns the user with the specified username.
	 */
	public UserProfile findUser(String username)
		throws DatabaseException {

		// Connect to the database:
		try {
			Statement statement
				= this.sqlConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs;
			
			rs = statement.executeQuery(
				"SELECT * FROM FreeSubscription WHERE " +
				"Username = \'" + username + "\';");
			
			if (rs.first())
				return new FreeSubscription(rs);

			rs = statement.executeQuery(
				"SELECT * FROM CheapSubscription WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return new CheapSubscription(rs);

			rs = statement.executeQuery(
				"SELECT * FROM ExpensiveSubscription WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return new ExpensiveSubscription(rs);

			rs = statement.executeQuery(
				"SELECT * FROM Operator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return new Operator(rs);

			rs = statement.executeQuery(
				"SELECT * FROM ExternalAdministrator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return new ExternalAdministrator(rs);

			rs = statement.executeQuery(
				"SELECT * FROM RegularAdministrator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return new RegularAdministrator(rs);

			rs = statement.executeQuery(
				"SELECT * FROM ExpertAdministrator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return new ExpertAdministrator(rs);

			throw new DatabaseException("Invalid username!");
		}

		// Exception handling:
		catch (SQLException e) {
			throw new DatabaseException(
				"SQL Exception: " + e.getMessage());
		}
		catch (ParseException e) {
			throw new DatabaseException(
				"Parse Exception: " + e.getMessage());
		}
	}

	/**
	 * Checks whether a user with the specified username exists.
	 */
	public boolean userExists(String username)
		throws DatabaseException {

		// Connect to the database:
		try {

			Statement statement = this.sqlConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs;
			
			rs = statement.executeQuery(
				"SELECT * FROM FreeSubscription WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return true;

			rs = statement.executeQuery(
				"SELECT * FROM CheapSubscription WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return true;

			rs = statement.executeQuery(
				"SELECT * FROM ExpensiveSubscription WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return true;

			rs = statement.executeQuery(
				"SELECT * FROM Operator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return true;

			rs = statement.executeQuery(
				"SELECT * FROM ExternalAdministrator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return true;

			rs = statement.executeQuery(
				"SELECT * FROM RegularAdministrator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return true;

			rs = statement.executeQuery(
				"SELECT * FROM ExpertAdministrator WHERE " +
				"Username = \'" + username + "\';");
			if (rs.first())
				return true;

			return false;
		}

		// Exception handling:
		catch (SQLException e) {
			throw new DatabaseException(
				"SQL Exception: " + e.getMessage());
		}
	}
}
