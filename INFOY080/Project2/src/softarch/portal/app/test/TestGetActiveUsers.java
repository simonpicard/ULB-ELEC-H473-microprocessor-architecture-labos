package softarch.portal.app.test;

import java.util.List;
import java.util.Iterator;

import softarch.portal.app.ApplicationFacade;
import softarch.portal.data.UserProfile;

/**
 * This is a test program for the application facade's
 * <code>getActiveUsers</code> method.
 * @author Niels Joncheere
 */
public class TestGetActiveUsers {
	public static void main(String[] args) {
		try {
			ApplicationFacade appFacade = new ApplicationFacade(
				"njonchee",
				"chivas12",
				"localhost/njonchee");
			System.out.println(appFacade.login("God", "7475"));
			System.out.println(appFacade.login("Nilipili", "7475"));
			System.out.println(appFacade.login("Niels82", "7475"));
			List users = appFacade.getActiveUsers();
			for (Iterator i = users.iterator(); i.hasNext(); )
				System.out.println(
					((UserProfile) i.next()).asXml());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
