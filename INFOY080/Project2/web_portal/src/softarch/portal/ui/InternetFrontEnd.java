package softarch.portal.ui;

import javax.servlet.http.HttpServlet;
import java.util.Map;
import java.util.Hashtable;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.net.URL;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import java.io.FileInputStream;

import softarch.portal.app.ApplicationFacade;

/**
 * This class implements the portal's internet front-end.
 * It is a <code>javax.servlet.http.HttpServlet</code> that
 * accepts HTTP GET and POST requests from the user and forwards
 * these requests to the appropriate page.
 * @author Niels Joncheere
 */
public class InternetFrontEnd extends HttpServlet {
	private Map 		pages		= new Hashtable();
	private Properties	properties	= new Properties();
	
	/**
	 * Handles HTTP GET requests.
	 * This method accepts an HTTP GET request, and forwards it to
	 * the page given in the request's <i>Page</i> parameter.
	 * This page will return an XML string that will be transformed
	 * to HTML using a <code>javax.xml.transform.Transformer</code>.
	 */
	public void doGet(	HttpServletRequest	request,
				HttpServletResponse	response)
		throws ServletException, IOException {

		String	parameter = request.getParameter("Page");
		Page	page;
		if (parameter != null && pages.containsKey(parameter))
			page = (Page) pages.get(parameter);
		else
			page = (Page) pages.get("Login");
		response.setContentType("text/html; charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
		try {
			StreamSource input
				= new StreamSource(
					new StringReader(
						page.doGet(request, response)));
			StreamSource stylesheet
				= new StreamSource(
					new URL("file", "",
						properties.getProperty(
							"stylesheet"))
								.openStream());
			StreamResult output
				= new StreamResult(out);
		
			TransformerFactory tf
				= TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer(stylesheet);
			transformer.transform(input, output);
		}
		catch (Exception e) {
			out.write(e.getMessage());
			e.printStackTrace(out);
		}
	}
	
	/**
	 * Handles HTTP POST requests.
	 * This method accepts an HTTP POST request, and forwards it to
	 * the page given in the request's <i>Page</i> parameter.
	 * This page will return an XML string that will be transformed
	 * to HTML using a <code>javax.xml.transform.Transformer</code>.
	 */
	public void doPost(	HttpServletRequest	request,
				HttpServletResponse	response)
		throws ServletException, IOException {

		String	parameter = request.getParameter("Page");
		Page	page;
		if (parameter != null && pages.containsKey(parameter))
			page = (Page) pages.get(parameter);
		else
			page = (Page) pages.get("Login");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			StreamSource input
				= new StreamSource(
					new StringReader(
						page.doPost(	request,
								response)));
			StreamSource stylesheet
				= new StreamSource(
					new URL("file", "",
						properties.getProperty(
							"stylesheet"))
								.openStream());
			StreamResult output
				= new StreamResult(out);
		
			TransformerFactory tf
				= TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer(stylesheet);
			transformer.transform(input, output);
		}
		catch (Exception e) {
			out.write(e.getMessage());
			e.printStackTrace(out);
		}
	}

	/**
	 * Initializes the <code>HttpServlet</code>.
	 */
	public void init() throws ServletException {
		try {
			String cfgfile = getInitParameter("config_file");
			properties.load(new FileInputStream(cfgfile));
			ApplicationFacade appFacade
				= new ApplicationFacade(
					properties.getProperty("dbUser"),
					properties.getProperty("dbPassword"),
					properties.getProperty("dbUrl"));
			pages.put(	"Registration",
					new RegistrationPage(appFacade));
			pages.put(	"Login",
					new LoginPage(appFacade));
			pages.put(	"Query",
					new QueryPage(appFacade));
			pages.put(	"Administration",
					new AdministrationPage(appFacade));
			pages.put(	"Operation",
					new OperationPage(appFacade));
			pages.put(	"Logout",
					new LogoutPage(appFacade));
		}
		catch (IOException e) {
			// This exception will be thrown when the servlet
			// cannot access the configuration file.  The behaviour
			// of the servlet is undefined in this case.
		}
	}

	/**
	 * Returns a string containing some basic information about the
	 * <code>HttpServlet</code>.
	 */
	public String getServletInfo() {
		return	"Semantic Web Portal -- Copyright (C) 2002-2003 " +
			"Niels Joncheere";
	}
}
