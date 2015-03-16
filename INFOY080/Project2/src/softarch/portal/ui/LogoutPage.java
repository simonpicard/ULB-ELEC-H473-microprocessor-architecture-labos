package softarch.portal.ui;

import softarch.portal.app.ApplicationException;
import softarch.portal.app.ApplicationFacade;
import softarch.portal.data.RegularData;
import softarch.portal.data.RegularUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

/**
 * This class implements the portal's logout page.
 * Functionality that doesn't change the state of the portal is provided by the
 * <code>doGet</code> method, functionality that does is provided by the
 * <code>doPost</code> method.
 * @author Niels Joncheere
 */
public class LogoutPage extends Page {
	/**
	 * Creates a new logout page.
	 * @param appFacade	The application facade the page should use to
	 * 			send its requests to.
	 */
	public LogoutPage(ApplicationFacade appFacade) {
		this.appFacade		= appFacade;
		this.title		= "Logout Page";
		this.description
			=	"This page allows you to log out of the " +
				"Semantic Web Portal.";
	}

	/**
	 * This method provides the functionality that does not change the state
	 * of the portal.
	 * @param request	The HTTP request that was sent to the server.
	 * @param response	The HTTP response that will be sent to the
	 * 			client.
	 * @return		An XML string that should be transformed to an
	 * 			HTML page informing the user that he is now
	 * 			logged out.
	 */
	public String doGet(	HttpServletRequest	request,
				HttpServletResponse	response) {
		try {
			Cookie[]	cookies		= request.getCookies();
			String		username	= new String();
			Number		sessionId	= new Integer(0);

			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("SWP_Username"))
					username = cookies[i].getValue();
				else if (cookies[i]
					.getName()
					.equals("SWP_SessionID"))
					
					sessionId = new Integer(
						cookies[i].getValue());
			}

			appFacade.logout(username, sessionId);

			return makePage("<LogoutPage_Get />");
		}
		catch (ApplicationException e) {
			return makePage(
				"<LogoutPage_Error>" +
				"<Message>" + e.getMessage() + "</Message>" +
				"</LogoutPage_Error>");
		}
		catch (Exception e) {
			return makePage(
				"<LogoutPage_Error>" +
				"<Message>The logout page has caught an " +
					"unexpected exception: " +
					e.getMessage() + "</Message>" +
				"</LogoutPage_Error>");
		}
	}

	/**
	 * This method provides the functionality that changes the state of the
	 * portal.
	 * @param request	The HTTP request that was sent to the server.
	 * @param response	The HTTP response that will be sent to the
	 * 			client.
	 * @return		An XML string that contains an error message 
	 * 			that says the logout page doesn't support
	 * 			HTTP POST requests.
	 */
	public String doPost(	HttpServletRequest	request,
				HttpServletResponse	response) {
		return makePage(
			"<LogoutPage_Error>" +
			"<Message>The logout page does not support HTTP post " +
				"requests.</Message>" +
			"</LogoutPage_Error>");
	}
}
