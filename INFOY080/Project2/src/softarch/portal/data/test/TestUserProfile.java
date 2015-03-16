package softarch.portal.data.test;

import java.util.Date;

import softarch.portal.data.ExternalAdministrator;

/**
 * This is a test program for the UserProfile class and its subclasses.
 * @author Niels Joncheere
 */
public class TestUserProfile {
	public static void main(String[] args) {
		ExternalAdministrator ea
			= new ExternalAdministrator(	
				"Niels82",
				"7475",
				"Niels",
				"Joncheere",
				"niels@joncheere.be",
				new Date());
		System.out.println(ea.asSql());
		System.out.println();
		System.out.println(ea.getDefaultPage());
	}
}
