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
 * This class implements the portal's query page.
 * Functionality that doesn't change the state of the portal is provided by
 * the <code>doGet</code> method.  As the query page never changes the state of
 * the portal, its <code>doPost</code> method will always return an error
 * message.
 * @author Niels Joncheere
 */
public class QueryPage extends Page {
	/**
	 * Creates a new query page.
	 * @param appFacade	The application facade the page should use to
	 * 			send its requests to.
	 */
	public QueryPage(ApplicationFacade appFacade) {
		this.appFacade		= appFacade;
		this.title		= "Query Page";
		this.description
			=	"This page allows you to retrieve " +
				"information stored in the Semantic Web " +
				"Portal.";
	}

	/**
	 * This method provides the functionality that does not change the
	 * state of the portal.
	 * @param request	The HTTP request that was sent to the server.
	 * @param response	The HTTP response that will be sent to the
	 * 			client.
	 * @return		An XML string that should be transformed to an
	 * 			HTML page containing some kind of query form
	 *			or the results of a query (depending on the
	 *			parameters that are provided in
	 *			<code>request</code>).
	 */
	public String doGet(	HttpServletRequest	request,
				HttpServletResponse	response) {
		try {
			String queryType = request.getParameter("QueryType");
			if (queryType != null
				&& queryType.equals("LatestAdditions")) {

				Cookie[] cookies
					= request.getCookies();
				String username
					= new String();
				Number sessionId
					= new Integer(0);

				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName()
						.equals("SWP_Username"))
						
						username = cookies[i]
							.getValue();
					
					else if (cookies[i].getName()
						.equals("SWP_SessionID"))
						
						sessionId = new Integer(
							cookies[i].getValue());
				}

				RegularUser profile
					= (RegularUser) appFacade
						.findUser(username, sessionId);
				Date lastLogin
					= profile.getLastLogin();

				String output = new String();
				output += "<QueryPage_Get>";
				output += "<QueryResult>";
				List records = appFacade.findRecordsFrom(
					request.getParameter("InformationType"),
					lastLogin);
				for (Iterator i = records.iterator();
					i.hasNext(); )
					
					output += ((RegularData) i.next())
						.asXml();
				
				output += "</QueryResult>";
				output += "</QueryPage_Get>";
				return makePage(output);
			}
			else if (queryType != null
				&& queryType.equals("FullText")) {
				
				String output = new String();
				output += "<QueryPage_Get>";
				output += "<QueryResult>";
				List records = appFacade.findRecords(
					request.getParameter("InformationType"),
					request.getParameter("QueryString"));
				for (Iterator i = records.iterator();
					i.hasNext(); )
					
					output += ((RegularData) i.next())
						.asXml();
				
				output += "</QueryResult>";
				output += "</QueryPage_Get>";
				return makePage(output);
			}
			else
				return makePage(
					"<QueryPage_Get>" +
					"<QueryForm />" +
					"</QueryPage_Get>");
		}
		catch (ApplicationException e) {
			return makePage(
				"<QueryPage_Error>" +
				"<Message>" + e.getMessage() + "</Message>" +
				"</QueryPage_Error>");
		}
		catch (Exception e) {
			return makePage(
				"<QueryPage_Error>" +
				"<Message>The query page has caught an " +
					"unexpected exception: " +
					e.getMessage() + "</Message>" +
				"</QueryPage_Error>");
		}
	}

	/**
	 * As the query page never changes the state of the portal, this method
	 * will always generate an error message.
	 * @param request	The HTTP request that was sent to the server.
	 * @param response	The HTTP response that will be sent to the
	 * 			client.
	 * @return		An XML string that contains an error message.
	 */
	public String doPost(	HttpServletRequest	request,
				HttpServletResponse	response) {

		return makePage(
			"<QueryPage_Error>" +
			"<Message>The query page does not support HTTP post " +
				"requests.</Message>" +
			"</QueryPage_Error>");
	}
}
