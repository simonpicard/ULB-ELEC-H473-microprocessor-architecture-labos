package softarch.portal.ui;

import softarch.portal.app.ApplicationException;
import softarch.portal.app.ApplicationFacade;
import softarch.portal.data.Article;
import softarch.portal.data.Book;
import softarch.portal.data.Conference;
import softarch.portal.data.InterestingWebsite;
import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;
import softarch.portal.data.Report;
import softarch.portal.data.SoftwareRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Iterator;
import java.util.Date;
import java.net.URL;

/**
 * This class implements the portal's administration page.
 * Functionality that doesn't change the state of the portal is provided by the
 * <code>doGet</code> method, functionality that does is provided by the
 * <code>doPost</code> method.
 * @author Niels Joncheere
 */
public class AdministrationPage extends Page {
	/**
	 * Creates a new administration page.
	 * @param appFacade	The application facade the page should use to
	 * 			send its requests to.
	 */
	public AdministrationPage(ApplicationFacade appFacade) {
		this.appFacade		= appFacade;
		this.title		= "Administration Page";
		this.description
			=	"This page allows you to add " +
				"information to the Sematic Web Portal.";
	}

	/**
	 * This method provides the functionality that does not change the state
	 * of the portal.
	 * @param request	The HTTP request that was sent to the server.
	 * @param response	The HTTP response that will be sent to the
	 * 			client.
	 * @return		An XML string that should be transformed to an
	 * 			HTML page containing some kind of administration
	 * 			form.
	 */
	public String doGet(	HttpServletRequest	request,
				HttpServletResponse	response) {
		try {
			String queryType
				= request.getParameter("QueryType");
			String output
				= new String();
			output += "<AdministrationPage_Get>";
			if (queryType == null)
				output += "<AdministrationForm />";
			else if (queryType.equals("Delete")) {
				RawData rd = appFacade.getRawData((new Integer(request.getParameter("ID"))).intValue());
				appFacade.deleteRawData(rd);

				return makePage(
					"<AdministrationPage_Post>" +
					rd.asXml() +
					"</AdministrationPage_Post>");

			}
			else if (queryType
				.equals("ViewUnvalidatedInformation")) {
				
				output += "<UnvalidatedInformation>";
				List unvalidatedRecords
					= appFacade.getRawData();
				for (Iterator i = unvalidatedRecords.iterator();
					i.hasNext(); )
					
					output += ((RawData) i.next()).asXml();
				output += "</UnvalidatedInformation>";
			}
			else if (queryType.equals("StructureOrValidate")) {
				int id = (new Integer(request
					.getParameter("ID"))).intValue();
				output += "<ValidationForm>";
				output += appFacade.getRawData(id).asXml();
				output += "</ValidationForm>";
			}
			else if (queryType.equals("AssignInformationType")) {
				String informationType = request
					.getParameter("InformationType");
				String id = request.getParameter("ID");
				output += "<AssignInformationType>";
				if (informationType == null) {
					output += "<AssignInformationTypeForm>";
					output += appFacade.getRawData(
						(new Integer(id)).intValue())
							.asXml();
					output +=
						"</AssignInformationTypeForm>";
				}
				else {
					output += "<" + informationType +
						"ValidationForm>";
					output += appFacade.getRawData(
						(new Integer(id)).intValue())
							.asXml();
					output += "</" + informationType +
						"ValidationForm>";
				}
				output += "</AssignInformationType>";
			}
			else if (queryType.equals("InsertInformation")) {
				String informationType = request.
					getParameter("InformationType");
				output += "<InsertInformation>";
				if (informationType == null)
					output += "<InformationTypeForm />";
				else
					output += "<Insert" + informationType +
						"Form />";
				output += "</InsertInformation>";
			}
			output += "</AdministrationPage_Get>";
			return makePage(output);
		}
		catch (ApplicationException e) {
			return makePage(
				"<AdministrationPage_Error>" +
				"<Message>" + e.getMessage() + "</Message>" +
				"</AdministrationPage_Error>");
		}
		catch (Exception e) {
			return makePage(
				"<AdministrationPage_Error>" +
				"<Message>The administration page has caught " +
				"an unexpected exception: " + e.getMessage() +
				"</Message>" +
				"</AdministrationPage_Error>");
		}
	}

	/**
	 * This method provides the functionality that changes the state of the
	 * portal.
	 * @param request	The HTTP request that was sent to the server.
	 * @param response	The HTTP response that will be sent to the
	 * 			client.
	 * @return		An XML string that contains the the information
	 * 			that the user requested.
	 */
	public String doPost(	HttpServletRequest	request,
				HttpServletResponse	response) {
		try {
			String queryType = request.getParameter("QueryType");
			if (queryType == null)
				throw new ApplicationException(
					"You did not provide a query type!");
			else if (queryType.equals("StructureOrValidate")) {
				String informationType = request
					.getParameter("InformationType");
				String id = request.getParameter("ID");
				RegularData rd;
				switch (informationType.charAt(0)) {
					case 'B':
						rd = new Book(request);
						break;
					case 'A':
						rd = new Article(request);
						break;
					case 'R':
						rd = new Report(request);
						break;
					case 'C':
						rd = new Conference(request);
						break;
					case 'S':
						rd = new SoftwareRepository(
							request);
						break;
					case 'I':
						rd = new InterestingWebsite(
							request);
						break;
					default:
						throw new ApplicationException(
							"You did not provide " +
							"a valid information " +
							"type!");
				}

				RawData rawData = appFacade
					.getRawData((new Integer(id))
						.intValue());
				rawData.setStructure(rd);

				String validate = request
					.getParameter("Validate");
				if (validate == null)
					appFacade.updateRawData(rawData);
				else {
					appFacade.deleteRawData(rawData);
					appFacade.add(rd);
				}
					
				return makePage(
					"<AdministrationPage_Post>" +
					rawData.asXml() +
					"</AdministrationPage_Post>");
			}
			else if (queryType.equals("InsertInformation")) {
				RegularData rd;
				switch (request
					.getParameter("InformationType")
					.charAt(0)) {
					
					case 'B':
						rd = new Book(request);
						break;
					case 'A':
						rd = new Article(request);
						break;
					case 'R':
						rd = new Report(request);
						break;
					case 'C':
						rd = new Conference(request);
						break;
					case 'S':
						rd = new SoftwareRepository(
							request);
						break;
					case 'I':
						rd = new InterestingWebsite(
							request);
						break;
					default:
						throw new ApplicationException(
							"You did not provide " +
							"a valid information " +
							"type!");
				}
				appFacade.addRawData(rd);
				return makePage(
					"<AdministrationPage_Post>" +
					rd.asXml() +
					"</AdministrationPage_Post>");
			}
			else throw new ApplicationException(
				"You did not provide a valid query type!");
		}
		catch (ApplicationException e) {
			return makePage(
				"<AdministrationPage_Error>" +
				"<Message>" + e.getMessage() + "</Message>" +
				"</AdministrationPage_Error>");
		}
		catch (Exception e) {
			return makePage(
				"<AdministrationPage_Error>" +
				"<Message>The administration page has caught " +
					"an unexpected exception: " +
					e.getMessage() + "</Message>" +
				"</AdministrationPage_Error>");
		}
	}
}
