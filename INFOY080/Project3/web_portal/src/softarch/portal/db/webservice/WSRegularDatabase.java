package softarch.portal.db.webservice;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import softarch.portal.data.Book;
import softarch.portal.data.RegularData;
import softarch.portal.db.DatabaseException;
import softarch.portal.db.RegularDatabase;

public class WSRegularDatabase implements RegularDatabase {

	private String wsLocation;
	
	public WSRegularDatabase(String wsLocation) {
		this.wsLocation = wsLocation;
	}
	
	public List<RegularData> findRecords(String informationType,
			String queryString) throws DatabaseException {
		
		List<RegularData> result = new Vector<RegularData>();
		
		switch (informationType.charAt(0)) {
			case 'B':
				WSLibrarySearch ws = new WSLibrarySearch(wsLocation);
				List<Book> books = ws.getBooks(queryString); 
				for(Book book : books) {
					result.add(book);
				}
				break;
			default:
				throw new DatabaseException("Invalid information type!");
		}
		return result;
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
