package softarch.portal.app;

import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;
import softarch.portal.data.UserProfile;
import softarch.portal.db.sql.DatabaseFacade;

import java.util.List;
import java.util.Date;

/**
 * This class implements a facade for all of the application layer's
 * functionality.
 * @author Niels Joncheere
 */
public class ApplicationFacade {
	private UserManager		userManager;
	private QueryManager		queryManager;
	private AdministrationManager	administrationManager;
	private OperationManager	operationManager;

	/**
	 * Creates a new application facade.
	 */
	public ApplicationFacade(	String dbUser,
					String dbPassword,
					String dbUrl) {
		
		DatabaseFacade dbFacade
			= new DatabaseFacade(dbUser, dbPassword, dbUrl);
		
		userManager		= new UserManager(dbFacade);
		queryManager		= new QueryManager(dbFacade);
		administrationManager	= new AdministrationManager(dbFacade);
		operationManager	= new OperationManager(dbFacade);
	}



	/**
	 * Adds a user profile to the user database.
	 * @param profile The profile to be added.
	 */
	public void add(UserProfile profile)
		throws ApplicationException {

		userManager.add(profile);
	}

	/**
	 * Returns the user profile for the user with the specified username.
	 */
	public UserProfile findUser(String username)
		throws ApplicationException {

		return userManager.findUser(username);
	}

	/**
	 * Returns the user profile for the user with the specified username,
	 * and throws an exception if his session ID does not match the one that
	 * is provided as a parameter.
	 */
	public UserProfile findUser(String username, Number sessionId)
		throws ApplicationException {

		return userManager.findUser(username, sessionId);
	}

	/**
	 * Logs in the user with the specified username and password.
	 */
	public Number login(String username, String password)
		throws ApplicationException {

		return userManager.login(username, password);
	}

	/**
	 * Returns a list of all users that are currently logged in.
	 */
	public List getActiveUsers()
		throws ApplicationException {

		return userManager.getActiveUsers();
	}

	/**
	 * Logs out the user with the specified username and session ID.
	 */
	public void logout(String username, Number sessionId)
		throws ApplicationException {

		userManager.logout(username, sessionId);
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

		return queryManager.findRecords(informationType, queryString);
	}

	/**
	 * Returns a list containing all records of the given information type
	 * that were added after the given date.
	 */
	public List findRecordsFrom(String informationType, Date date)
		throws ApplicationException {

		return queryManager.findRecordsFrom(informationType, date);
	}



	/**
	 * Adds a new regular data object to the regular database.
	 * @param rd	The regular data object that should be added to
	 * 		the regular database.
	 */
	public void add(RegularData rd)
		throws ApplicationException {

		administrationManager.add(rd);
	}

	/**
	 * Returns a list that contains all raw data that is currently stored
	 * in the raw database.
	 */
	public List getRawData()
		throws ApplicationException {

		return administrationManager.getRawData();
	}

	/**
	 * Returns a specific raw data object.
	 */
	public RawData getRawData(int id)
		throws ApplicationException {

		return administrationManager.getRawData(id);
	}

	/**
	 * Creates a new raw data object with an empty source.
	 * The structure of the object is specified by the <code>rd</code>
	 * parameter.
	 */
	public void addRawData(RegularData rd)
		throws ApplicationException {

		administrationManager.addRawData(rd);
	}

	/**
	 * Deletes a raw data object.
	 * @param rd	The raw data object that should be deleted.
	 */
	public void deleteRawData(RawData rd)
		throws ApplicationException {

		administrationManager.deleteRawData(rd);
	}

	/**
	 * Updates a raw data object.
	 * @param rd	The raw data object that should be updated.
	 */
	public void updateRawData(RawData rd)
		throws ApplicationException {

		administrationManager.updateRawData(rd);
	}



	/**
	 * Returns the number of records of the given information type in the
	 * regular database.
	 */
	public int getNumberOfRegularRecords(String informationType)
		throws ApplicationException {

		return operationManager.getNumberOfRegularRecords(informationType);
	}

	/**
	 * Returns the number of records in the raw database.
	 */
	public int getNumberOfRawRecords()
		throws ApplicationException {

		return operationManager.getNumberOfRawRecords();
	}
}
