package softarch.portal.data;

/**
 * This class is a superclass for all regular user accounts.
 * @author Niels Joncheere
 */
public abstract class RegularUser extends UserProfile {
	/**
	 * When a regular user has logged in successfully, he will be
	 * redirected to this page.
	 */
	public String getDefaultPage() {
		return "web_portal?Page=Query";
	}
}
