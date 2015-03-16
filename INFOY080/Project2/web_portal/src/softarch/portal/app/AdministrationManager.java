package softarch.portal.app;

import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;
import softarch.portal.db.DatabaseException;
import softarch.portal.db.DatabaseFacade;

import java.util.List;

/**
 * This class handles all requests that that are needed to allow the
 * user to structure and/or validate information.
 * @author Niels Joncheere
 */
public class AdministrationManager extends Manager {
	/**
	 * Creates a new administration manager.
	 * @param dbFacade	The database facade the manager should use
	 * 			to access the database.
	 */
	public AdministrationManager(DatabaseFacade dbFacade) {
		this.dbFacade = dbFacade;
	}

	/**
	 * Adds a new regular data object to the regular database.
	 * @param rd	The regular data object that should be added to
	 * 		the regular database.
	 */
	public void add(RegularData rd)
		throws ApplicationException {

		try {
			dbFacade.add(rd);
		}
		catch (DatabaseException e) {
			throw new ApplicationException(e.getMessage());
		}
		catch (Exception e) {
			throw new ApplicationException(
				"The administration manager has caught an " +
				"unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Returns a list that contains all raw data that is currently stored
	 * in the raw database.
	 */
	public List getRawData()
		throws ApplicationException {

		try {
			return dbFacade.getRawData();
		}
		catch (DatabaseException e) {
			throw new ApplicationException(e.getMessage());
		}
		catch (Exception e) {
			throw new ApplicationException(
				"The administration manager has caught an " +
				"unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Returns a specific raw data object.
	 */
	public RawData getRawData(int id)
		throws ApplicationException {

		try {
			return dbFacade.getRawData(id);
		}
		catch (DatabaseException e) {
			throw new ApplicationException(e.getMessage());
		}
		catch (Exception e) {
			throw new ApplicationException(
				"The administration manager has caught an " +
				"unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Creates a new raw data object with an empty source.
	 * The structure of the object is specified by the <code>rd</code>
	 * parameter.
	 */
	public void addRawData(RegularData rd)
		throws ApplicationException {

		try {
			dbFacade.addRawData(rd);
		}
		catch (DatabaseException e) {
			throw new ApplicationException(e.getMessage());
		}
		catch (Exception e) {
			throw new ApplicationException(
				"The administration manager has caught an " +
				"unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Deletes a raw data object.
	 * @param rd	The raw data object that should be Deleted.
	 */
	public void deleteRawData(RawData rd)
		throws ApplicationException {

		try {
			dbFacade.deleteRawData(rd);
		}
		catch (DatabaseException e) {
			throw new ApplicationException(e.getMessage());
		}
		catch (Exception e) {
			throw new ApplicationException(
				"The administration manager has caught an " +
				"unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Updates a raw data object.
	 * @param rd	The raw data object that should be updated.
	 */
	public void updateRawData(RawData rd)
		throws ApplicationException {

		try {
			dbFacade.updateRawData(rd);
		}
		catch (DatabaseException e) {
			throw new ApplicationException(e.getMessage());
		}
		catch (Exception e) {
			throw new ApplicationException(
				"The administration manager has caught an " +
				"unexpected exception: " + e.getMessage());
		}
	}
}
