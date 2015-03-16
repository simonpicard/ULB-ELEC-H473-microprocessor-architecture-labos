package softarch.portal.ui;

import softarch.portal.app.ApplicationException;
import softarch.portal.app.ApplicationFacade;
import softarch.portal.data.CheapSubscription;
import softarch.portal.data.ExpensiveSubscription;
import softarch.portal.data.FreeSubscription;
import softarch.portal.data.UserProfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * This class implements the portal's registration page.
 * Functionality that doesn't change the state of the portal is provided by the
 * <code>doGet</code> method, functionality that does is provided by the
 * <code>doPost</code> method.
 * @author Niels Joncheere
 */
public class RegistrationPage extends Page {
	/**
	 * Creates a new registration page.
	 * @param appFacade	The application facade the page should use to
	 * 			send its requests to.
	 */
	public RegistrationPage(ApplicationFacade appFacade) {
		this.appFacade		= appFacade;
		this.title		= "Registration Page";
		this.description
			=	"Using the registration form on this page, " +
				"you can request an account for the Semantic " +
				"Web Portal.";
	}

	/**
	 * This method provides the functionality that does not change the state
	 * of the portal.
	 * @param request	The HTTP request that was sent to the server.
	 * @param response	The HTTP response that will be sent to the
	 * 			client.
	 * @return		An XML string that should be transformed to an
	 * 			HTML page containing some kind of registration
	 * 			form.
	 */
	public String doGet(	HttpServletRequest	request,
				HttpServletResponse	response) {
		return makePage("<RegistrationPage_Get />");
	}

	/**
	 * This method provides the functionality that changes the state of the
	 * portal.
	 * @param request	The HTTP request that was sent to the server.
	 * @param response	The HTTP response that will be sent to the
	 * 			client.
	 * @return		An XML string that contains the user profile
	 * 			that has been created, or an error message if
	 * 			something has gone wrong.
	 */
	public String doPost(	HttpServletRequest	request,
				HttpServletResponse	response) {
		try {
			if (!request
				.getParameter("Password")
				.equals(request.getParameter("RepeatPassword")))

				throw new ApplicationException(
					"The passwords you provided do not " +
					"match.  Please try again.");

			if (request
				.getParameter("Username")
				.equals(""))
				
				throw new ApplicationException(
					"You did not provide a username.  " +
					"Please try again.");

			UserProfile up;
			switch (request
				.getParameter("Subscription")
				.charAt(0)) {

				case 'F':
					up = new FreeSubscription(request);
					break;
				case 'C':
					up = new CheapSubscription(request);
					break;
				case 'E':
					up = new ExpensiveSubscription(request);
					break;
				default:
					throw new ApplicationException(
						"You did not provide a valid " +
						"subscription type.  Please " +
						"try again.");
			}

			appFacade.add(up);

			return makePage(
				"<RegistrationPage_Post>" +
				up.asXml() +
				"</RegistrationPage_Post>");
		}
		catch (ApplicationException e) {
			return makePage(
				"<RegistrationPage_Error>" +
				"<Message>" + e.getMessage() + "</Message>" +
				"</RegistrationPage_Error>");
		}
		catch (Exception e) {
			return makePage(
				"<RegistrationPage_Error>" +
				"<Message>The registration page has caught " +
					"an unexpected exception: " +
					e.getMessage() + "</Message>" +
				"</RegistrationPage_Error>");
		}
	}
}
