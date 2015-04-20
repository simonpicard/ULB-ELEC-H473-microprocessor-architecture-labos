package softarch.portal.data;

import java.util.Date;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.net.MalformedURLException;
import javax.servlet.http.HttpServletRequest;

/**
 * This class represents an interesting website.
 * @author Niels Joncheere
 */
public class InterestingWebsite extends RegularData {
	private String	author;
	private String	review;
	private String	summary;
	private String	title;
	private URL	url;

	/**
	 * Creates a new interesting website.
	 */
	public InterestingWebsite(	Date	dateAdded,
					String	author,
					String	review,
					String	summary,
					String	title,
					URL	url) {

		this.dateAdded	= dateAdded;
		this.author	= author;
		this.review	= review;
		this.summary	= summary;
		this.title	= title;
		this.url	= url;
	}

	/**
	 * Creates a new interesting website from a
	 * <code>java.sql.ResultSet</code> object.
	 * @see java.sql.ResultSet
	 */
	public InterestingWebsite(ResultSet rs)
		throws SQLException, ParseException, MalformedURLException {

		this(	df.parse(rs.getString("DateAdded")),
			rs.getString("Author"),
			rs.getString("Review"),
			rs.getString("Summary"),
			rs.getString("Title"),
			new URL(rs.getString("URL")));
	}

	/**
	 * Creates a new interesting website from a
	 * <code>javax.servlet.http.HttpServletRequest</code> object.
	 * @see javax.servlet.http.HttpServletRequest
	 */
	public InterestingWebsite(HttpServletRequest request)
		throws ParseException, MalformedURLException {

		this(	new Date(),
			request.getParameter("Author"),
			request.getParameter("Review"),
			request.getParameter("Summary"),
			request.getParameter("Title"),
			new URL(request.getParameter("URL")));
	}

	/**
	 * Returns an XML representation of the object.
	 */
	public String asXml() {
		return	"<InterestingWebsite>" +
			"<dateAdded>" + df.format(dateAdded) + "</dateAdded>" +
			"<author>" + normalizeXml(author) + "</author>" +
			"<review>" + normalizeXml(review) + "</review>" +
			"<summary>" + normalizeXml(summary) + "</summary>" +
			"<title>" + normalizeXml(title) + "</title>" +
			"<url>" + normalizeXml(url.toString()) + "</url>" +
			"</InterestingWebsite>";
	}

	/**
	 * Returns an SQL INSERT string that allows the system to add the
	 * interesting website to a relational database.
	 */
	public String asSql() {
		return	"INSERT INTO InterestingWebsite (DateAdded, Author, " +
			"Review, Summary, Title, URL) " + "VALUES (\'" +
			df.format(dateAdded) + "\', \'" + normalizeSql(author) +
			"\', \'" + normalizeSql(review) + "\', \'" + 
			normalizeSql(summary) + "\', \'" + normalizeSql(title) +
			"\', \'" + normalizeSql(url.toString()) + "\');";
	}

	/**
	 * Returns an SQL INSERT string that allows the system to add a
	 * <code>RawData</code> object with an <code>InterestingWebsite</code>
	 * structure to a relational database.
	 * @see softarch.portal.data.RawData
	 */
	public String asSql(RawData rd) {
		return	"INSERT INTO RawInterestingWebsite (ID, " +
			"DateAdded, Author, Review, Summary, Title, URL) " +
			"VALUES (" + rd.getId() + ", \'" +
			df.format(dateAdded) + "\', \'" + normalizeSql(author) +
			"\', \'" + normalizeSql(review) + "\', \'" +
			normalizeSql(summary) + "\', \'" + normalizeSql(title) +
			"\', \'" + normalizeSql(url.toString()) + "\');";
	}

	/**
	 * Returns an SQL DELETE string that allows the system to delete a
	 * <code>RawData</code> object with an <code>InterestingWebsite</code>
	 * structure from a relational database.
	 * @see softarch.portal.data.RawData
	 */
	public String asSqlDelete(RawData rd) {
		return	"DELETE FROM RawInterestingWebsite WHERE ID = " +
			rd.getId() + ";";
	}
}
