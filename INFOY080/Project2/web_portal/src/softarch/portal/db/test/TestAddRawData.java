package softarch.portal.db.test;

import softarch.portal.data.RawData;
import softarch.portal.data.SoftwareRepository;
import softarch.portal.db.RawDatabase;

import java.util.Date;
import java.net.URL;

/**
 * This is a test program for the raw database's
 * <code>addRawData</code> method.
 * @author Niels Joncheere
 */
public class TestAddRawData {
	public static void main(String[] args) {
		try {
			RawDatabase rawDb = new RawDatabase(
				"njonchee",
				"chivas12",
				"localhost/njonchee");
			RawData rd = new RawData(0, "boe");
			rd.setStructure(new SoftwareRepository(
				new Date(),
				"Nilipili",
				"Niels\' Repository",
				new URL("http://void")));
//			rawDb.addRawData(rd);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
