package softarch.portal.data;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;

/**
 * This class represents an article.
 * @author Niels Joncheere
 */
public class Article extends RegularData {
	private String	author;
	private Date	publicationDate;
	private String	review;
	private String	summary;
	private String	title;

	/**
	 * Creates a new article.
	 */
	public Article(	Date	dateAdded,
			String	author,
			Date	publicationDate,
			String	review,
			String	summary,
			String	title) {

		this.dateAdded		= dateAdded;
		this.author		= author;
		this.publicationDate	= publicationDate;
		this.review		= review;
		this.summary		= summary;
		this.title		= title;
	}

	/**
	 * Creates a new article from a
	 * <code>java.sql.ResultSet</code> object.
	 * @see java.sql.ResultSet
	 */
	public Article(ResultSet rs)
		throws SQLException, ParseException {

		this(	df.parse(rs.getString("DateAdded")),
			rs.getString("Author"),
			df.parse(rs.getString("PublicationDate")),
			rs.getString("Review"),
			rs.getString("Summary"),
			rs.getString("Title"));
	}

	/**
	 * Creates a new article from a
	 * <code>javax.servlet.http.HttpServletRequest</code> object.
	 * @see javax.servlet.http.HttpServletRequest
	 */
	public Article(HttpServletRequest request)
		throws ParseException {

		this(	new Date(),
			request.getParameter("Author"),
			df.parse(request.getParameter("PublicationDate")),
			request.getParameter("Review"),
			request.getParameter("Summary"),
			request.getParameter("Title"));
	}

	/**
	 * Returns an XML representation of the object.
	 */
	public String asXml() {
		return	"<Article>" +
			"<dateAdded>" + df.format(dateAdded) + "</dateAdded>" +
			"<author>" + normalizeXml(author) + "</author>" +
			"<publicationDate>" +
			df.format(publicationDate) +
			"</publicationDate>" +
			"<review>" + normalizeXml(review) + "</review>" +
			"<summary>" + normalizeXml(summary) + "</summary>" +
			"<title>" + normalizeXml(title) + "</title>" +
			"</Article>";
	}

	/**
	 * Returns an SQL INSERT string that allows the system to add the
	 * article to a relational database.
	 */
	public String asSql() {
		return	"INSERT INTO Article (DateAdded, Author, " +
			"PublicationDate, Review, Summary, Title) VALUES (\'" +
			df.format(dateAdded) + "\', \'" + normalizeSql(author) +
			"\', \'" + df.format(publicationDate) + "\', \'" +
			normalizeSql(review) + "\', \'" +
			normalizeSql(summary) + "\', \'" + normalizeSql(title) +
			"\');";
	}

	/**
	 * Returns an SQL INSERT string that allows the system to add a
	 * <code>RawData</code> object with an <code>Article</code>
	 * structure to a relational database.
	 * @see softarch.portal.data.RawData
	 */
	public String asSql(RawData rd) {
		return	"INSERT INTO RawArticle (ID, DateAdded, Author, " +
			"PublicationDate, Review, Summary, Title) VALUES (" +
			rd.getId() + ", \'" +
			df.format(dateAdded) + "\', \'" + normalizeSql(author) +
			"\', \'" + df.format(publicationDate) + "\', \'" +
			normalizeSql(review) + "\', \'" +
			normalizeSql(summary) + "\', \'" + normalizeSql(title) +
			"\');";
	}

	/**
	 * Returns an SQL DELETE string that allows the system to delete a
	 * <code>RawData</code> object with an <code>Article</code>
	 * structure from a relational database.
	 * @see softarch.portal.data.RawData
	 */
	public String asSqlDelete(RawData rd) {
		return "DELETE FROM RawArticle WHERE ID = " + rd.getId() + ";";
	}
}
