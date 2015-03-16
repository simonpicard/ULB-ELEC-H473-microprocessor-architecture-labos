package softarch.portal.data;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;

/**
 * This class represents a book.
 * @author Niels Joncheere
 */
public class Book extends RegularData {
	private String	author;
	private long	isbn;
	private int	pages;
	private Date	publicationDate;
	private String	publisher;
	private String	review;
	private String	summary;
	private String	title;

	/**
	 * Creates a new book.
	 */
	public Book(	Date	dateAdded,
			String	author,
			long	isbn,
			int	pages,
			Date	publicationDate,
			String	publisher,
			String	review,
			String	summary,
			String	title) {

		this.dateAdded		= dateAdded;
		this.author		= author;
		this.isbn		= isbn;
		this.pages		= pages;
		this.publicationDate	= publicationDate;
		this.publisher		= publisher;
		this.review		= review;
		this.summary		= summary;
		this.title		= title;
	}

	/**
	 * Creates a new book from a
	 * <code>java.sql.ResultSet</code> object.
	 * @see java.sql.ResultSet
	 */
	public Book(ResultSet rs)
		throws SQLException, ParseException {

		this(	df.parse(rs.getString("DateAdded")),
			rs.getString("Author"),
			rs.getLong("ISBN"),
			rs.getInt("Pages"),
			df.parse(rs.getString("PublicationDate")),
			rs.getString("Publisher"),
			rs.getString("Review"),
			rs.getString("Summary"),
			rs.getString("Title"));
	}

	/**
	 * Creates a new book from a
	 * <code>javax.servlet.http.HttpServletRequest</code> object.
	 * @see javax.servlet.http.HttpServletRequest
	 */
	public Book(HttpServletRequest request)
		throws ParseException {

		this(	new Date(),
			request.getParameter("Author"),
			new Long(request.getParameter("ISBN")).longValue(),
			new Integer(request.getParameter("Pages")).intValue(),
			df.parse(request.getParameter("PublicationDate")),
			request.getParameter("Publisher"),
			request.getParameter("Review"),
			request.getParameter("Summary"),
			request.getParameter("Title"));
	}

	/**
	 * Returns an XML representation of the object.
	 */
	public String asXml() {
		return	"<Book>" +
			"<dateAdded>" + df.format(dateAdded) + "</dateAdded>" +
			"<author>" + normalizeXml(author) + "</author>" +
			"<isbn>" + isbn + "</isbn>" +
			"<pages>" + pages + "</pages>" +
			"<publicationDate>" +
			df.format(publicationDate) +
			"</publicationDate>" +
			"<publisher>" +
			normalizeXml(publisher) +
			"</publisher>" +
			"<review>" + normalizeXml(review) + "</review>" +
			"<summary>" + normalizeXml(summary) + "</summary>" +
			"<title>" + normalizeXml(title) + "</title>" +
			"</Book>";
	}

	/**
	 * Returns an SQL INSERT string that allows the system to add the
	 * book to a relational database.
	 */
	public String asSql() {
		return	"INSERT INTO Book (DateAdded, Author, ISBN, Pages, " +
			"PublicationDate, Publisher, Review, Summary, Title) " +
			"VALUES " + "(\'" + df.format(dateAdded) + "\', \'" +
			normalizeSql(author) + "\', " + isbn + ", " + pages +
			", \'" + df.format(publicationDate) + "\', \'" +
			normalizeSql(publisher) + "\', \'" +
			normalizeSql(review) + "\', \'" +
			normalizeSql(summary) + "\', \'" + normalizeSql(title) +
			"\');";
	}

	/**
	 * Returns an SQL INSERT string that allows the system to add a
	 * <code>RawData</code> object with a <code>Book</code>
	 * structure to a relational database.
	 * @see softarch.portal.data.RawData
	 */
	public String asSql(RawData rd) {
		return	"INSERT INTO RawBook (ID, DateAdded, Author, " +
			"ISBN, Pages, PublicationDate, Publisher, Review, " +
			"Summary, Title) VALUES (" + rd.getId() + ", \'" +
			df.format(dateAdded) + "\', \'" + normalizeSql(author) +
			"\', " + isbn + ", " + pages + ", \'" +
			df.format(publicationDate) + "\', \'" +
			normalizeSql(publisher) + "\', \'" +
			normalizeSql(review) + "\', \'" +
			normalizeSql(summary) + "\', \'" +
			normalizeSql(title) + "\');";
	}

	/**
	 * Returns an SQL DELETE string that allows the system to delete a
	 * <code>RawData</code> object with an <code>Book</code>
	 * structure from a relational database.
	 * @see softarch.portal.data.RawData
	 */
	public String asSqlDelete(RawData rd) {
		return "DELETE FROM RawBook WHERE ID = " + rd.getId() + ";";
	}
}
