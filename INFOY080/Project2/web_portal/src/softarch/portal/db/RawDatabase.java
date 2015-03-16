package softarch.portal.db;

import java.util.List;

import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;

public interface RawDatabase {

	/**
	 * Returns a list of all raw data.
	 */
	public List<RawData> getRawData() throws DatabaseException;
	/**
	 * Returns a specific raw data object.
	 */
	public RawData getRawData(int id) throws DatabaseException;
	
	public void addRawData(RegularData regularData) throws DatabaseException;
	/**
	 * Deletes a raw data object.
	 */
	public void deleteRawData(RawData rd) throws DatabaseException;
	/**
	 * Updates a raw data object.
	 */
	public void updateRawData(RawData rd) throws DatabaseException;
	/**
	 * Returns the number of records in the raw database.
	 */
	public int getNumberOfRawRecords() throws DatabaseException;
}
