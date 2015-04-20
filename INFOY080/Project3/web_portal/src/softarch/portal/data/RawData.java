package softarch.portal.data;

import java.util.List;
import java.util.Vector;

/**
 * This class represents a <i>raw data</i> object (an object
 * that has not been structured or validated yet).
 * @author Niels Joncheere
 */
public class RawData extends Data {
	private int		id;
	private String		source;
	private RegularData	structure;

	/**
	 * Creates a new <i>raw data</i> object.
	 */
	public RawData(int id, String source) {
		this.id		= id;
		this.source	= source;
	}

	public RawData(int id, RegularData structure) {
		this.id		= id;
		this.source	= new String();
		this.structure	= structure;
	}

	public int getId() {
		return id;
	}

	public String getSource() {
		return source;
	}

	public RegularData getStructure() {
		return structure;
	}

	public RawData setStructure(RegularData structure) {
		this.structure = structure;
		return this;
	}

	/**
	 * Returns an XML representation of the object.
	 */
	public String asXml() {
		String result = new String();
		result += "<RawData>";
		result += "<id>" + id + "</id>";
		result += "<source>" + normalizeXml(source) + "</source>";
		// The following check could be avoided by using a dummy
		// structure (whose asXml method returns an empty string)
		// until the raw data object is structured.
		if (structure != null)
			result +=	"<structure>" +
					structure.asXml() +
					"</structure>";
		result += "</RawData>";
		return result;
	}

	/**
	 * Returns an SQL INSERT string that allows the system to add the
	 * <i>raw data</i> object to a relational database.
	 */
	public List asSql() {
		List queries = new Vector();
		// The following check could be avoided by using a dummy
		// structure (whose asSql method returns the SQL INSERT
		// string below) until the raw data object is structured.
		if (structure == null)
			queries.add("INSERT INTO Raw (ID, Source, Structured) VALUES (" + id + ", \'" + normalizeSql(source) + "\', 0);");
		else {
			queries.add("INSERT INTO Raw (ID, Source, Structured) VALUES (" + id + ", \'" + normalizeSql(source) + "\', 1);");
			queries.add(structure.asSql(this));
		}
		return queries;
	}

	/**
	 * Returns an SQL DELETE string that allows the system to delete the
	 * <i>raw data</i> object from a relational database.
	 */
	public String asSqlDelete() {
		// The following check could be avoided by using a dummy
		// structure (whose asSqlDelete method returns the SQL DELETE
		// string below) until the raw data object is structured.
		if (structure == null)
			return "DELETE FROM Raw WHERE ID = " + id + ";";
		else
			return structure.asSqlDelete(this);
	}
}
