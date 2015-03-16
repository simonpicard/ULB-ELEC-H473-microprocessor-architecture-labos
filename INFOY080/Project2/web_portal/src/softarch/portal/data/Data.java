package softarch.portal.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * This is an abstract superclass for all data types used by
 * the Semantic Web Portal.
 * @author Niels Joncheere
 */
public abstract class Data {
	/**
	 * The portal's date format.
	 */
	protected static final DateFormat df
		= new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Returns an XML representation of the object.
	 */
	public abstract String asXml();

	/**
	 * Removes illegal XML characters from the given input string.
	 */
	protected String normalizeXml(String input) {
		String	result	= new String();
		int	length	= input.length();

		for (int i = 0; i < length; i++) {
			char c = input.charAt(i);

			switch (c) {
				case '<':
					result += "&lt;";
					break;
				case '>':
					result += "&gt;";
					break;
				case '&':
					result += "&amp;";
					break;
				case '\'':
					result += "&apos;";
					break;
				case '\"':
					result += "&quot;";
					break;
				default:
					result += c;
			}
		}
		return result;
	}

	/**
	 * Removes illegal SQL characters from the given input string.
	 */
	protected String normalizeSql(String input) {
		String	result	= new String();
		int	length	= input.length();

		for (int i = 0; i < length; i++) {
			char c = input.charAt(i);

			switch (c) {
				case '\'':
					result += "\\\'";
					break;
				case '\"':
					result += "\\\"";
					break;
				default:
					result += c;
			}
		}
		return result;
	}
}
