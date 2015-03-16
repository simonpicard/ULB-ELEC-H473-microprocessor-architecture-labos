package softarch.portal.db.sql;

import java.util.List;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import softarch.portal.data.Article;
import softarch.portal.data.Book;
import softarch.portal.data.Conference;
import softarch.portal.data.InterestingWebsite;
import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;
import softarch.portal.data.Report;
import softarch.portal.data.SoftwareRepository;
import softarch.portal.db.DatabaseException;
import softarch.portal.db.RawDatabase;

import java.sql.SQLException;

/**
 * This class encapsulates the portal's raw database.
 * @author Niels Joncheere
 */
public class SqlRawDatabase implements RawDatabase {
	
	protected SqlConnection sqlConnection;
	
	/**
	 * Creates a new raw database.
	 */
	public SqlRawDatabase(String dbUser, String dbPassword, String dbUrl) {
		this.sqlConnection = new SqlConnection(dbUser, dbPassword, dbUrl);
	}

	/**
	 * Returns a list of all raw data.
	 */
	public List<RawData> getRawData()
		throws DatabaseException {

		// Connect to the database:
		try {
			List<RawData> result
				= new Vector<RawData>();
			
			Statement statement
				= this.sqlConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs
				= statement.executeQuery("SELECT * FROM Raw WHERE Structured <> 1;");
			while (rs.next()) {
				RawData rawData
					= new RawData(	rs.getInt("ID"),
							rs.getString("Source"));
				result.add(rawData);
			}

			rs = statement.executeQuery("SELECT * FROM Raw NATURAL JOIN RawBook;");
			while (rs.next()) {
				RawData rawData
					= new RawData(	rs.getInt("ID"),
							rs.getString("Source"));
				result.add(rawData.setStructure(new Book(rs)));
			}

			rs = statement
				.executeQuery("SELECT * FROM Raw NATURAL JOIN RawArticle;");
			while (rs.next()) {
				RawData rawData
					= new RawData(	rs.getInt("ID"),
							rs.getString("Source"));
				result.add(rawData.setStructure(
							new Article(rs)));
			}

			rs = statement
				.executeQuery("SELECT * FROM Raw NATURAL JOIN RawReport;");
			while (rs.next()) {
				RawData rawData
					= new RawData(	rs.getInt("ID"),
							rs.getString("Source"));
				result.add(rawData.setStructure(
							new Report(rs)));
			}

			rs = statement
				.executeQuery("SELECT * FROM Raw NATURAL JOIN RawConference;");
			while (rs.next()) {
				RawData rawData
					= new RawData(	rs.getInt("ID"),
							rs.getString("Source"));
				result.add(rawData.setStructure(
							new Conference(rs)));
			}

			rs = statement
				.executeQuery("SELECT * FROM Raw NATURAL JOIN RawSoftwareRepository;");
			while (rs.next()) {
				RawData rawData
					= new RawData(	rs.getInt("ID"),
							rs.getString("Source"));
				result.add(
					rawData.setStructure(
						new SoftwareRepository(rs)));
			}

			rs = statement
				.executeQuery("SELECT * FROM Raw NATURAL JOIN RawInterestingWebsite;");
			while (rs.next()) {
				RawData rawData
					= new RawData(	rs.getInt("ID"),
							rs.getString("Source"));
				result.add(
					rawData.setStructure(
						new InterestingWebsite(rs)));
			}
			
			return result;
		}

		// Exception handling:
		catch (SQLException e) {
			throw new DatabaseException(
				"SQL Exception: " + e.getMessage());
		}
		catch (Exception e) {
			throw new DatabaseException(
				"The raw database has caught an unexpected " +
				"exception: " + e.getMessage());
		}
	}

	private int getNewId()
		throws DatabaseException {

		// Connect to the database:
		try {
			Statement statement = this.sqlConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = statement.executeQuery("SELECT MAX(ID) AS ID FROM Raw;");

			if (rs.first())
				return rs.getInt("ID") + 1;
			else
				throw new DatabaseException("The raw database could not find a new id!");
		}

		// Exception handling:
		catch (SQLException e) {
			throw new DatabaseException(
				"SQL Exception: " + e.getMessage());
		}
		catch (DatabaseException e) {
			throw e;
		}
		catch (Exception e) {
			throw new DatabaseException(
				"The raw database has caught an unexpected " +
				"exception: " + e.getMessage());
		}
	}

	/**
	 * Returns a specific raw data object.
	 */
	public RawData getRawData(int id)
		throws DatabaseException {

		// Connect to the database:
		try {
			Statement statement = this.sqlConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = statement.executeQuery("SELECT * FROM Raw WHERE Structured <> 1 AND ID = " + id + ";");
			if (rs.first()) {
				RawData rawData
					= new RawData(	rs.getInt("ID"),
							rs.getString("Source"));
				return rawData;
			}

			rs = statement.executeQuery("SELECT * FROM Raw NATURAL JOIN RawBook WHERE Raw.ID = " + id + ";");
			if (rs.first()) {
				RawData rawData
					= new RawData(	rs.getInt("ID"),
							rs.getString("Source"));
				return rawData.setStructure(new Book(rs));
			}

			rs = statement.executeQuery("SELECT * FROM Raw NATURAL JOIN RawArticle WHERE Raw.ID = " + id + ";");
			if (rs.first()) {
				RawData rawData
					= new RawData(	rs.getInt("ID"),
							rs.getString("Source"));
				return rawData.setStructure(new Article(rs));
			}

			rs = statement.executeQuery("SELECT * FROM Raw NATURAL JOIN RawReport WHERE Raw.ID = " + id + ";");
			if (rs.first()) {
				RawData rawData
					= new RawData(	rs.getInt("ID"),
							rs.getString("Source"));
				return rawData.setStructure(new Report(rs));
			}

			rs = statement
				.executeQuery("SELECT * FROM Raw NATURAL JOIN RawConference WHERE Raw.ID = " + id + ";");
			if (rs.first()) {
				RawData rawData
					= new RawData(	rs.getInt("ID"),
							rs.getString("Source"));
				return rawData.setStructure(new Conference(rs));
			}

			rs = statement
				.executeQuery("SELECT * FROM Raw NATURAL JOIN RawSoftwareRepository WHERE Raw.ID = " + id + ";");
			if (rs.first()) {
				RawData rawData
					= new RawData(	rs.getInt("ID"),
							rs.getString("Source"));
				return rawData.setStructure(new SoftwareRepository(rs));
			}

			rs = statement
				.executeQuery("SELECT * FROM Raw NATURAL JOIN RawInterestingWebsite WHERE Raw.ID = " + id + ";");
			if (rs.first()) {
				RawData rawData
					= new RawData(	rs.getInt("ID"),
							rs.getString("Source"));
				return rawData.setStructure(new InterestingWebsite(rs));
			}

			throw new DatabaseException("The raw data object with ID " + id + " was not found in the database.");
		}

		// Exception handling:
		catch (SQLException e) {
			throw new DatabaseException(
				"SQL Exception: " + e.getMessage());
		}
		catch (DatabaseException e) {
			throw e;
		}
		catch (Exception e) {
			throw new DatabaseException(
				"The raw database has caught an unexpected " +
				"exception: " + e.getMessage());
		}
	}

	public void addRawData(RegularData regularData)
		throws DatabaseException {

		int id = getNewId();
		RawData rawData = new RawData(id, regularData);
		this.sqlConnection.executeSql(rawData.asSql());
	}

	/**
	 * Deletes a raw data object.
	 */
	public void deleteRawData(RawData rd)
		throws DatabaseException {

		int id = rd.getId();
		this.sqlConnection.executeSql("DELETE FROM Raw WHERE ID = " + id + ";");
		this.sqlConnection.executeSql("DELETE FROM RawBook WHERE ID = " + id + ";");
		this.sqlConnection.executeSql("DELETE FROM RawArticle WHERE ID = " + id + ";");
		this.sqlConnection.executeSql("DELETE FROM RawReport WHERE ID = " + id + ";");
		this.sqlConnection.executeSql("DELETE FROM RawConference WHERE ID = " + id + ";");
		this.sqlConnection.executeSql("DELETE FROM RawSoftwareRepository WHERE ID = " + id + ";");
		this.sqlConnection.executeSql("DELETE FROM RawInterestingWebsite WHERE ID = " + id + ";");
	}

	/**
	 * Updates a raw data object.
	 */
	public void updateRawData(RawData rd)
		throws DatabaseException {

		deleteRawData(rd);
		this.sqlConnection.executeSql(rd.asSql());
	}

	/**
	 * Returns the number of records in the raw database.
	 */
	public int getNumberOfRawRecords()
		throws DatabaseException {

		// Connect to the database:
		try {
			Statement statement
				= this.sqlConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs
				= statement.executeQuery(
					"SELECT COUNT(*) \"Count\" FROM Raw;");

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
				"The raw database has caught an unexpected " +
				"exception: " + e.getMessage());
		}
	}
}
