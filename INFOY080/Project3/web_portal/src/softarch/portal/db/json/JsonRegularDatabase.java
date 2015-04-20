package softarch.portal.db.json;

import java.util.Date;
import java.util.List;

import softarch.portal.data.RegularData;
import softarch.portal.db.DatabaseException;
import softarch.portal.db.RegularDatabase;

public class JsonRegularDatabase implements RegularDatabase {

	public List<RegularData> findRecords(String informationType,
			String queryString) throws DatabaseException {
		throw new DatabaseException("Not implemented yet.");
	}

	public List<RegularData> findRecordsFrom(String informationType, Date date)
			throws DatabaseException {
		throw new DatabaseException("Not implemented yet.");
	}

	public void add(RegularData rd) throws DatabaseException {
		throw new DatabaseException("Not implemented yet.");
	}

	public int getNumberOfRegularRecords(String informationType)
			throws DatabaseException {
		throw new DatabaseException("Not implemented yet.");
	}

}
