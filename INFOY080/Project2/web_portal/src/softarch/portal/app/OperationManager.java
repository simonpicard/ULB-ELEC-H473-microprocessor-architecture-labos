package softarch.portal.app;

import softarch.portal.db.sql.DatabaseException;
import softarch.portal.db.sql.DatabaseFacade;

/**
 * This class is responsible for handling requests for diagnostic information,
 * such as the number of records in the database(s), the users currently
 * logged in, etc.
 * @author Niels Joncheere
 */
public class OperationManager extends Manager {
	/**
	 * Creates a new operation manager.
	 * @param dbFacade	The database facade the manager should use to
	 * 			access the portal's database(s).
	 */
	public OperationManager(DatabaseFacade dbFacade) {
		this.dbFacade = dbFacade;
	}

	/**
	 * Returns the number of records of the given information type in the
	 * regular database.
	 */
	public int getNumberOfRegularRecords(String informationType)
		throws ApplicationException {

		try {
			return dbFacade.getNumberOfRegularRecords(informationType);
		}
		catch (DatabaseException e) {
			throw new ApplicationException(e.getMessage());
		}
		catch (Exception e) {
			throw new ApplicationException(
				"The operation manager has caught an " +
				"unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Returns the number of records in the raw database.
	 */
	public int getNumberOfRawRecords()
		throws ApplicationException {

		try {
			return dbFacade.getNumberOfRawRecords();
		}
		catch (DatabaseException e) {
			throw new ApplicationException(e.getMessage());
		}
		catch (Exception e) {
			throw new ApplicationException(
				"The operation manager has caught an " +
				"unexpected exception: " + e.getMessage());
		}
	}
}
