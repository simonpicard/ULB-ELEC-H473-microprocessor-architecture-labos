package softarch.portal.data;

import java.util.Date;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.net.MalformedURLException;
import javax.servlet.http.HttpServletRequest;

/**
 * This class represents a software repository.
 * @author Niels Joncheere
 */
public class SoftwareRepository extends RegularData {
	private String	author;
	private String	name;
	private URL	url;

	/**
	 * Creates a new software repository.
	 */
	public SoftwareRepository(	Date	dateAdded,
					String	author,
					String	name,
					URL	url) {

		this.dateAdded	= dateAdded;
		this.author	= author;
		this.name	= name;
		this.url	= url;
	}

	/**
	 * Creates a new software repository from a
	 * <code>java.sql.ResultSet</code> object.
	 * @see java.sql.ResultSet
	 */
	public SoftwareRepository(ResultSet rs)
		throws SQLException, ParseException, MalformedURLException {

		this(	df.parse(rs.getString("DateAdded")),
			rs.getString("Author"),
			rs.getString("Name"),
			new URL(rs.getString("URL")));
	}

	/**
	 * Creates a new software repository from a
	 * <code>javax.servlet.http.HttpServletRequest</code> object.
	 * @see javax.servlet.http.HttpServletRequest
	 */
	public SoftwareRepository(HttpServletRequest request)
		throws ParseException, MalformedURLException {

		this(	new Date(),
			request.getParameter("Author"),
			request.getParameter("Name"),
			new URL(request.getParameter("URL")));
	}

	/**
	 * Returns an XML representation of the object.
	 */
	public String asXml() {
		return	"<SoftwareRepository>" +
			"<dateAdded>" + df.format(dateAdded) + "</dateAdded>" +
			"<author>" + normalizeXml(author) + "</author>" +
			"<name>" + normalizeXml(name) + "</name>" +
			"<url>" + normalizeXml(url.toString()) + "</url>" +
			"</SoftwareRepository>";
	}

	/**
	 * Returns an SQL INSERT string that allows the system to add the
	 * software repository to a relational database.
	 */
	public String asSql() {
		return	"INSERT INTO SoftwareRepository (DateAdded, Author, " +
			"Name, URL) VALUES (\'" + df.format(dateAdded) +
			"\', \'" + normalizeSql(author) + "\', \'" +
			normalizeSql(name) + "\', \'" +
			normalizeSql(url.toString()) + "\');";
	}

	/**
	 * Returns an SQL INSERT string that allows the system to add a
	 * <code>RawData</code> object with a <code>SoftwareRepository</code>
	 * structure to a relational database.
	 * @see softarch.portal.data.RawData
	 */
	public String asSql(RawData rd) {
		return	"INSERT INTO RawSoftwareRepository (ID, " +
			"DateAdded, Author, Name, URL) VALUES (\'" +
			rd.getId() + "\', \'" +
			df.format(dateAdded) + "\', \'" +
			normalizeSql(author) + "\', \'" + normalizeSql(name) +
			"\', \'" + normalizeSql(url.toString()) + "\');";
	}

	/**
	 * Returns an SQL DELETE string that allows the system to delete a
	 * <code>RawData</code> object with a <code>SoftwareRepository</code>
	 * structure from a relational database.
	 * @see softarch.portal.data.RawData
	 */
	public String asSqlDelete(RawData rd) {
		return	"DELETE FROM RawSoftwareRepository WHERE ID = " +
			rd.getId() + ";";
	}
}
