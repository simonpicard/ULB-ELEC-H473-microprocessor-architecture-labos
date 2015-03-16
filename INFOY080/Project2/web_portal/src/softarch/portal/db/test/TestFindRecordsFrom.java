package softarch.portal.db.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import softarch.portal.data.SoftwareRepository;
import softarch.portal.db.RegularDatabase;

import java.net.URL;
import java.util.List;
import java.util.Iterator;

/**
 * This is a test program for the regular database's
 * <code>findRecordsFrom</code> method.
 * @author Niels Joncheere
 */
public class TestFindRecordsFrom {
	public static void main(String[] args) {
		try {
			RegularDatabase regularDb = new RegularDatabase(
				"njonchee",
				"chivas12",
				"localhost/njonchee");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = df.parse("2003-05-20");
			Date date2 = df.parse("2003-05-21");
			Date date3 = df.parse("2003-05-22");
			regularDb.add(new SoftwareRepository(
						date1,
						"sr1",
						"sr1",
						new URL("http://sr1.org")));
			regularDb.add(new SoftwareRepository(
						date2,
						"sr2",
						"sr2",
						new URL("http://sr2.org")));
			regularDb.add(new SoftwareRepository(
						date3,
						"sr3",
						"sr3",
						new URL("http://sr3.org")));
			List result = regularDb.findRecordsFrom(
							"SoftwareRepository",
							df.parse("2003-05-20"));
			for (Iterator i = result.iterator(); i.hasNext(); )
				System.out.println(
					((SoftwareRepository) i.next())
						.asXml());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
