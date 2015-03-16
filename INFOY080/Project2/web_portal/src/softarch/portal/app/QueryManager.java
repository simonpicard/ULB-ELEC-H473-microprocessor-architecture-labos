package softarch.portal.app;

import softarch.portal.db.DatabaseException;
import softarch.portal.db.DatabaseFacade;

import java.util.List;
import java.util.Date;

/**
 * Handles all queries concerning the regular database.
 * @author Niels Joncheere
 */
public class QueryManager extends Manager {
	/**
	 * Creates a new query manager.
	 * @param dbFacade	The database facade the manager should use to
	 * 			access the database.
	 */
	public QueryManager(DatabaseFacade dbFacade) {
		this.dbFacade = dbFacade;
	}

	/**
	 * Returns a list containing all records of the given information type
	 * that match the given query string.
	 * @param informationType	The information type the user wants
	 * 				to query.
	 * @param queryString		The query string that should be used
	 * 				to carry out the search (for example
	 * 				"+foo -bar").
	 */
	public List findRecords(String informationType, String queryString)
		throws ApplicationException {

		try {
			return dbFacade.findRecords(informationType, queryString);
		}
		catch (DatabaseException e) {
			throw new ApplicationException(e.getMessage());
		}
		catch (Exception e) {
			throw new ApplicationException(
				"The query manager has caught an unexpected " +
				"exception: " + e.getMessage());
		}
	}

	/**
	 * Returns a list containing all records of the given information type
	 * that were added after the given date.
	 */
	public List findRecordsFrom(String informationType, Date date)
		throws ApplicationException {

		try {
			return dbFacade.findRecordsFrom(informationType, date);
		}
		catch (DatabaseException e) {
			throw new ApplicationException(e.getMessage());
		}
		catch (Exception e) {
			throw new ApplicationException(
				"The query manager has caught an unexpected " +
				"exception: " + e.getMessage());
		}
	}
}
