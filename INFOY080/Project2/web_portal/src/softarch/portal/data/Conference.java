package softarch.portal.data;

import java.util.Date;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.net.MalformedURLException;
import javax.servlet.http.HttpServletRequest;

/**
 * This class represents a conference.
 * @author Niels Joncheere
 */
public class Conference extends RegularData {
	private Date	date;
	private String	location;
	private String	name;
	private String	review;
	private String	summary;
	private URL	url;

	/**
	 * Creates a new conference.
	 */
	public Conference(	Date	dateAdded,
				Date	date,
				String	location,
				String	name,
				String	review,
				String	summary,
				URL	url) {

		this.dateAdded	= dateAdded;
		this.date	= date;
		this.location	= location;
		this.name	= name;
		this.review	= review;
		this.summary	= summary;
		this.url	= url;
	}

	/**
	 * Creates a new conference from a
	 * <code>java.sql.ResultSet</code> object.
	 * @see java.sql.ResultSet
	 */
	public Conference(ResultSet rs)
		throws SQLException, ParseException, MalformedURLException {

		this(	df.parse(rs.getString("DateAdded")),
			df.parse(rs.getString("Date")),
			rs.getString("Location"),
			rs.getString("Name"),
			rs.getString("Review"),
			rs.getString("Summary"),
			new URL(rs.getString("URL")));
	}

	/**
	 * Creates a new conference from a
	 * <code>javax.servlet.http.HttpServletRequest</code> object.
	 * @see javax.servlet.http.HttpServletRequest
	 */
	public Conference(HttpServletRequest request)
		throws ParseException, MalformedURLException {

		this(	new Date(),
			df.parse(request.getParameter("Date")),
			request.getParameter("Location"),
			request.getParameter("Name"),
			request.getParameter("Review"),
			request.getParameter("Summary"),
			new URL(request.getParameter("URL")));
	}

	/**
	 * Returns an XML representation of the object.
	 */
	public String asXml() {
		return	"<Conference>" +
			"<dateAdded>" + df.format(dateAdded) + "</dateAdded>" +
			"<date>" + df.format(date) + "</date>" +
			"<location>" + normalizeXml(location) + "</location>" +
			"<name>" + normalizeXml(name) + "</name>" +
			"<review>" + normalizeXml(review) + "</review>" +
			"<summary>" + normalizeXml(summary) + "</summary>" +
			"<url>" + normalizeXml(url.toString()) + "</url>" +
			"</Conference>";
	}

	/**
	 * Returns an SQL INSERT string that allows the system to add the
	 * conference to a relational database.
	 */
	public String asSql() {
		return	"INSERT INTO Conference (DateAdded, Date, Location, " +
			"Name, Review, Summary, URL) VALUES (\'" +
			df.format(dateAdded) + "\', \'" + df.format(date) +
			"\', \'" + normalizeSql(location) + "\', \'" +
			normalizeSql(name) + "\', \'" + normalizeSql(review) +
			"\', \'" + normalizeSql(summary) + "\', \'" +
			normalizeSql(url.toString()) + "\');";
	}

	/**
	 * Returns an SQL INSERT string that allows the system to add a
	 * <code>RawData</code> object with a <code>Conference</code>
	 * structure to a relational database.
	 * @see softarch.portal.data.RawData
	 */
	public String asSql(RawData rd) {
		return	"INSERT INTO RawConference (ID, DateAdded, Date, " +
			"Location, Name, Review, Summary, URL) VALUES (" +
			rd.getId() + ", \'" +
			df.format(dateAdded) + "\', \'" + df.format(date) +
			"\', \'" + normalizeSql(location) + "\', \'" +
			normalizeSql(name) + "\', \'" + normalizeSql(review) +
			"\', \'" + normalizeSql(summary) + "\', \'" +
			normalizeSql(url.toString()) + "\');";
	}

	/**
	 * Returns an SQL DELETE string that allows the system to delete a
	 * <code>RawData</code> object with a <code>Conference</code>
	 * structure from a relational database.
	 * @see softarch.portal.data.RawData
	 */
	public String asSqlDelete(RawData rd) {
		return	"DELETE FROM RawConference WHERE ID = " + rd.getId() +
			";";
	}
}
