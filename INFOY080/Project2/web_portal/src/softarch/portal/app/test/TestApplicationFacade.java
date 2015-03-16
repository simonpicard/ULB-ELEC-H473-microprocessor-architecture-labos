package softarch.portal.app.test;

import softarch.portal.app.ApplicationFacade;
import softarch.portal.data.FreeSubscription;

import java.util.Date;

/**
 * This is a test program for the application facade.
 * @author Niels Joncheere
 */
public class TestApplicationFacade {
	public static void main(String[] args) {
		try {
			FreeSubscription fs = new FreeSubscription(
				"Niels82",
				"7475",
				"Niels",
				"Joncheere",
				"niels@joncheere.be",
				new Date());
			ApplicationFacade appFacade = new ApplicationFacade(
				"njonchee",
				"chivas12",
				"localhost/njonchee");
			appFacade.add(fs);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
