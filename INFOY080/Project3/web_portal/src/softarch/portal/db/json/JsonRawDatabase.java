package softarch.portal.db.json;

import java.util.List;

import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;
import softarch.portal.db.DatabaseException;
import softarch.portal.db.RawDatabase;

public class JsonRawDatabase implements RawDatabase {

	public List<RawData> getRawData() throws DatabaseException {
		throw new DatabaseException("Not implemented yet.");
	}

	public RawData getRawData(int id) throws DatabaseException {
		throw new DatabaseException("Not implemented yet.");
	}

	public void addRawData(RegularData regularData) throws DatabaseException {
		throw new DatabaseException("Not implemented yet.");
	}

	public void deleteRawData(RawData rd) throws DatabaseException {
		throw new DatabaseException("Not implemented yet.");
	}

	public void updateRawData(RawData rd) throws DatabaseException {
		throw new DatabaseException("Not implemented yet.");
	}

	public int getNumberOfRawRecords() throws DatabaseException {
		throw new DatabaseException("Not implemented yet.");
	}

}
