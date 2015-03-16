package softarch.portal.db;

import java.util.Date;
import java.util.List;

import softarch.portal.data.RegularData;

public interface RegularDatabase {

	/**
	 * Returns a list containing all records of the given information type
	 * that match the given query string.
	 */
	public List<RegularData> findRecords(String informationType, String queryString)
		throws DatabaseException;
	
	/**
	 * Returns a list containing all records of the given information type
	 * that were added after the given date.
	 */
	public List<RegularData> findRecordsFrom(String informationType, Date date)
		throws DatabaseException;
	
	/**
	 * Adds a new regular data object to the regular database.
	 */
	public void add(RegularData rd)
		throws DatabaseException;
	
	/**
	 * Returns the number of records of the given information type in the
	 * regular database.
	 */
	public int getNumberOfRegularRecords(String informationType)
		throws DatabaseException;
}
