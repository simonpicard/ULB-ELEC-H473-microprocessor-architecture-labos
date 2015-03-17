package softarch.portal.db.json;

import java.util.Date;
import java.util.List;

import softarch.portal.data.RegularData;
import softarch.portal.db.DatabaseException;
import softarch.portal.db.RegularDatabase;

public class JsonRegularDatabase implements RegularDatabase {

	public List<RegularData> findRecords(String informationType,
			String queryString) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RegularData> findRecordsFrom(String informationType, Date date)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(RegularData rd) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	public int getNumberOfRegularRecords(String informationType)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

}
