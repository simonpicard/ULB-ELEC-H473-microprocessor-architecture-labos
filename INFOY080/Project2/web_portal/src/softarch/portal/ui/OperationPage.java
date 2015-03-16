package softarch.portal.ui;

import softarch.portal.app.ApplicationException;
import softarch.portal.app.ApplicationFacade;
import softarch.portal.data.UserProfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Iterator;

/**
 * This class implements the portal's operation page.
 * Functionality that doesn't change the state of the portal is provided by the
 * <code>doGet</code> method, functionality that does is provided by the
 * <code>doPost</code> method.
 * @author Niels Joncheere
 */
public class OperationPage extends Page {
	/**
	 * Creates a new operation page.
	 * @param appFacade	The application facade the page should use to
	 * 			send its requests to.
	 */
	public OperationPage(ApplicationFacade appFacade) {
		this.appFacade		= appFacade;
		this.title		= "Operation Page";
		this.description
			=	"This page allows you to inspect the status " +
				"of the Semantic Web Portal and start or " +
				"stop its agents.";
	}

	/**
	 * This method provides the functionality that does not change the state
	 * of the portal.
	 * @param request	The HTTP request that was sent to the server.
	 * @param response	The HTTP response that will be sent to the
	 * 			client.
	 * @return		An XML string that should be transformed to an
	 * 			HTML page containing some kind of operation
	 * 			form.
	 */
	public String doGet(HttpServletRequest	request,
	                    HttpServletResponse	response) {
		try {
			String output = new String();
			output += "<OperationPage_Get>";
			output += "<NumberOfBooks>" +
				appFacade.getNumberOfRegularRecords("Book") +
				"</NumberOfBooks>";
			output += "<NumberOfArticles>" +
				appFacade.getNumberOfRegularRecords("Article") +
				"</NumberOfArticles>";
			output += "<NumberOfReports>" +
				appFacade.getNumberOfRegularRecords("Report") +
				"</NumberOfReports>";
			output += "<NumberOfConferences>" +
				appFacade.getNumberOfRegularRecords(
					"Conference") +
				"</NumberOfConferences>";
			output += "<NumberOfSoftwareRepositories>" +
				appFacade.getNumberOfRegularRecords(
					"SoftwareRepository") +
				"</NumberOfSoftwareRepositories>";
			output += "<NumberOfInterestingWebsites>" +
				appFacade.getNumberOfRegularRecords(
					"InterestingWebsite") +
				"</NumberOfInterestingWebsites>";
			output += "<NumberOfRawData>" +
				appFacade.getNumberOfRawRecords() +
				"</NumberOfRawData>";
			output += "<ActiveUsers>";
			List users = appFacade.getActiveUsers();
			for (Iterator i = users.iterator(); i.hasNext(); )
				output += ((UserProfile) i.next()).asXml();
			output += "</ActiveUsers>";
			output += "</OperationPage_Get>";
			return makePage(output);
		}
		catch (ApplicationException e) {
			return makePage(
				"<OperationPage_Error>" +
				"<Message>" + e.getMessage() + "</Message>" +
				"</OperationPage_Error>");
		}
		catch (Exception e) {
			return makePage(
				"<OperationPage_Error>" +
				"<Message>The operation page has caught an " +
					"unexpected exception: " +
					e.getMessage() + "</Message>" +
				"</OperationPage_Error>");
		}
	}

	/**
	 * This method provides the functionality that changes the state of the
	 * portal.
	 * @param request	The HTTP request that was sent to the server.
	 * @param response	The HTTP response that will be sent to the
	 * 			client.
	 */
	public String doPost(HttpServletRequest		request,
	                     HttpServletResponse	response) {
		return makePage("<OperationPage_Post />");
	}
}
