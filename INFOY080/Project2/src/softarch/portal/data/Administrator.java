package softarch.portal.data;

/**
 * This class is a superclass for all administrator accounts.
 * @author Niels Joncheere
 */
public abstract class Administrator extends UserProfile {
	/**
	 * When an administrator has logged in successfully, he will be
	 * redirected to this page.
	 */
	public String getDefaultPage() {
		return "web_portal?Page=Administration";
	}
}
