package softarch.portal.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

/**
 * Represents an <i>expert administrator</i> user account.
 * @author Niels Joncheere
 */
public class ExpertAdministrator extends Administrator {
	/**
	 * Creates a new <i>expert administrator</i> account from a
	 * <code>java.sql.ResultSet</code> object.
	 * @see java.sql.ResultSet
	 */
	public ExpertAdministrator(ResultSet rs)
		throws SQLException, ParseException {
	
		this.username		= rs.getString("Username");
		this.password		= rs.getString("Password");
		this.firstName		= rs.getString("FirstName");
		this.lastName		= rs.getString("LastName");
		this.emailAddress	= rs.getString("EmailAddress");
		this.lastLogin		= df.parse(rs.getString("LastLogin"));
	}

	/**
	 * Creates a new <i>expert administrator</i> account.
	 */
	public ExpertAdministrator (	String	username,
					String	password,
					String	firstName,
					String	lastName,
					String	emailAddress,
					Date	lastLogin) {
					
		this.username		= username;
		this.password		= password;
		this.firstName		= firstName;
		this.lastName		= lastName;
		this.emailAddress	= emailAddress;
		this.lastLogin		= lastLogin;
	}

	/**
	 * Returns an XML representation of the object.
	 */
	public String asXml() {
	
		return	"<ExpertAdministrator>" +
			"<username>" + normalizeXml(username) + "</username>" +
			// password is not returned,
			// as it should only be used internally
			"<firstName>" +
			normalizeXml(firstName) +
			"</firstName>" +
			"<lastName>" + normalizeXml(lastName) + "</lastName>" +
			"<emailAddress>" +
			normalizeXml(emailAddress) +
			"</emailAddress>" +
			"<lastLogin>" + df.format(lastLogin) + "</lastLogin>" +
			"</ExpertAdministrator>";
	}

	/**
	 * Returns an SQL INSERT string that allows the system to add
	 * the account to a relational database.
	 */
	public String asSql() {
		return	"INSERT INTO ExpertAdministrator (Username, " +
			"Password, FirstName, LastName, EmailAddress, " +
			"LastLogin) VALUES (\'" + normalizeSql(username) +
			"\', \'" + normalizeSql(password) +"\', \'" +
			normalizeSql(firstName) + "\', \'" +
			normalizeSql(lastName) + "\', \'" +
			normalizeSql(emailAddress) + "\', \'" +
			df.format(lastLogin) + "\');";
	}

	/**
	 * Returns an SQL UPDATE string that allows the system to update
	 * the account in a relational database.
	 */
	public String asSqlUpdate() {
		return  "UPDATE ExpertAdministrator SET Password = \'" +
			normalizeSql(password) + "\', FirstName = \'" +
			normalizeSql(firstName) + "\', LastName = \'" +
			normalizeSql(lastName) + "\', EmailAddress = \'" +
			normalizeSql(emailAddress) + "\', LastLogin = \'" +
			df.format(lastLogin) + "\' " + "WHERE Username = \'" +
			normalizeSql(username) + "\';";
	}
}
