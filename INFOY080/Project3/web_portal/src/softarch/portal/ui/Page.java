package softarch.portal.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import softarch.portal.app.ApplicationFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * This is an abstract superclass for all of the portal's pages.
 * @author Niels Joncheere
 */
public abstract class Page {
	protected String	title;
	protected String	description;
	
	protected static final DateFormat df
		= new SimpleDateFormat("yyyy-MM-dd");
	
	protected ApplicationFacade appFacade;

	/**
	 * This method returns an XML string that represents the page's
	 * response to an HTML GET request.
	 */
	public abstract String doGet(	HttpServletRequest	request,
					HttpServletResponse	response);

	/**
	 * This method returns an XML string that represents the page's
	 * response to an HTML POST request.
	 */
	public abstract String doPost(	HttpServletRequest	request,
					HttpServletResponse	response);

	/**
	 * This method constructs a <i>page</i> from an XML input string.
	 */
	protected String makePage(String input) {
		String output = new String();
		output += "<Page>";
		output += "<Title>" + title + "</Title>";
		output += "<Description>" + description + "</Description>";
		output += input;
		output += "<Time>" + new Date() + "</Time>";
		output += "</Page>";
		return output;
	}
}
