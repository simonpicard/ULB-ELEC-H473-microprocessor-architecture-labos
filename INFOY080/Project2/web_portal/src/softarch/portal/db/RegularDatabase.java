package softarch.portal.db;

import java.util.List;
import java.util.Date;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import softarch.portal.data.Article;
import softarch.portal.data.Book;
import softarch.portal.data.Conference;
import softarch.portal.data.InterestingWebsite;
import softarch.portal.data.RegularData;
import softarch.portal.data.Report;
import softarch.portal.data.SoftwareRepository;

import java.text.ParseException;
import java.sql.SQLException;

/**
 * This class encapsulates the regular database.
 * @author Niels Joncheere
 */
public class RegularDatabase extends Database {
	/**
	 * Creates a new regular database.
	 */
	public RegularDatabase(String dbUser, String dbPassword, String dbUrl) {
		super(dbUser, dbPassword, dbUrl);
	}

	/**
	 * Returns a list containing all records of the given information type
	 * that match the given query string.
	 */
	public List findRecords(String informationType, String queryString)
		throws DatabaseException {

		queryString = "%" + queryString + "%";
		
		// Connect to the database:
		try {
			List result = new Vector();

			Statement statement = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs;

			switch (informationType.charAt(0)) {
				case 'B':
					rs = statement.executeQuery("SELECT * FROM Book WHERE Author LIKE \'" + queryString + "\' OR Publisher LIKE \'" + queryString + "\' OR Review LIKE \'" + queryString + "\' OR Summary LIKE \'" + queryString + "\' OR Title LIKE \'" + queryString + "\' ORDER BY DateAdded DESC;");
					while (rs.next())
						result.add(new Book(rs));
					return result;
				case 'A':
					rs = statement.executeQuery("SELECT * FROM Article WHERE MATCH (Author, Review, Summary, Title) AGAINST (\'" + queryString + "\' IN BOOLEAN MODE) ORDER BY DateAdded DESC;");
					while (rs.next())
						result.add(new Article(rs));
					return result;
				case 'R':
					rs = statement.executeQuery("SELECT * FROM Report WHERE MATCH (Author, Review, Summary, Title) AGAINST (\'" + queryString + "\' IN BOOLEAN MODE) ORDER BY DateAdded DESC;");
					while (rs.next())
						result.add(new Report(rs));
					return result;
				case 'C':
					rs = statement.executeQuery("SELECT * FROM Conference WHERE MATCH (Location, Name, Review, Summary) AGAINST (\'" + queryString + "\' IN BOOLEAN MODE) ORDER BY DateAdded DESC;");
					while (rs.next())
						result.add(new Conference(rs));
					return result;
				case 'S':
					rs = statement.executeQuery("SELECT * FROM SoftwareRepository WHERE MATCH (Author, Name) AGAINST (\'" + queryString + "\' IN BOOLEAN MODE) ORDER BY DateAdded DESC;");
					while (rs.next())
						result.add(new SoftwareRepository(rs));
					return result;
				case 'I':
					rs = statement.executeQuery("SELECT * FROM InterestingWebsite WHERE MATCH (Author, Review, Summary, Title) AGAINST (\'" + queryString + "\' IN BOOLEAN MODE) ORDER BY DateAdded DESC;");
					while (rs.next())
						result.add(new InterestingWebsite(rs));
					return result;
				default:
					throw new DatabaseException(
						"Invalid information type!");
			}
		}

		// Exception handling:
		catch (ParseException e) {
			throw new DatabaseException(
				"Parse Exception: " + e.getMessage());
		}
		catch (SQLException e) {
			throw new DatabaseException(
				"SQL Exception: " + e.getMessage());
		}
		catch (Exception e) {
			throw new DatabaseException(
				"The regular database has caught an " +
				"unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Returns a list containing all records of the given information type
	 * that were added after the given date.
	 */
	public List findRecordsFrom(String informationType, Date date)
		throws DatabaseException {

		// Connect to the database:
		try {
			List result = new Vector();

			Statement statement = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs;

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			String query =	"SELECT * FROM " + informationType +
					" WHERE DateAdded >= \'" +
					df.format(date) + "\' ORDER BY " +
					"DateAdded DESC;";

			switch (informationType.charAt(0)) {
				case 'B':
					rs = statement.executeQuery(query);
					while (rs.next())
						result.add(new Book(rs));
					return result;
				case 'A':
					rs = statement.executeQuery(query);
					while (rs.next())
						result.add(new Article(rs));
					return result;
				case 'R':
					rs = statement.executeQuery(query);
					while (rs.next())
						result.add(new Report(rs));
					return result;
				case 'C':
					rs = statement.executeQuery(query);
					while (rs.next())
						result.add(new Conference(rs));
					return result;
				case 'S':
					rs = statement.executeQuery(query);
					while (rs.next())
						result.add(new SoftwareRepository(rs));
					return result;
				case 'I':
					rs = statement.executeQuery(query);
					while (rs.next())
						result.add(new InterestingWebsite(rs));
					return result;
				default:
					throw new DatabaseException(
						"Invalid information type!");
			}
		}

		// Exception handling:
		catch (ParseException e) {
			throw new DatabaseException(
				"Parse Exception: " + e.getMessage());
		}
		catch (SQLException e) {
			throw new DatabaseException(
				"SQL Exception: " + e.getMessage());
		}
		catch (Exception e) {
			throw new DatabaseException(
				"The regular database has caught an " +
				"unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Adds a new regular data object to the regular database.
	 */
	public void add(RegularData rd)
		throws DatabaseException {
		
		executeSql(rd.asSql());
	}

	/**
	 * Returns the number of records of the given information type in the
	 * regular database.
	 */
	public int getNumberOfRegularRecords(String informationType)
		throws DatabaseException {

		// Connect to the database:
		try {
			Statement statement = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = statement.executeQuery(
				"SELECT COUNT(*) \"Count\" FROM " +
				informationType + ";");
			rs.first();
			return rs.getInt("Count");
		}

		// Exception handling:
		catch (SQLException e) {
			throw new DatabaseException(
				"SQL Exception: " + e.getMessage());
		}
		catch (Exception e) {
			throw new DatabaseException(
				"The regular database has caught an " +
				"unexpected exception: " + e.getMessage());
		}
	}
}
