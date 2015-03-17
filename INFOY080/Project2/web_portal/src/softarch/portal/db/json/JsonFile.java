package softarch.portal.db.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class JsonFile {
	
	private String fileName;
	
	public JsonFile(String fileName) {
		this.fileName = fileName;
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void appendXml(String xmlSourceToAppend) throws IOException, JSONException {
		String jsonSource = readFile();
		JSONObject jsonObject = null;
		if(!"".equals(jsonSource)) {
			try {
				jsonObject = new JSONObject(jsonSource);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		String xmlSource = "";
		if(!"".equals(jsonSource)) {
			try {
				xmlSource = XML.toString(jsonObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		xmlSource += xmlSourceToAppend;
		jsonObject = XML.toJSONObject(xmlSource);
		write(jsonObject.toString());
	}

	public String readFile() throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
	public void write(String jsonSource) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(fileName);
		writer.print("");
		writer.write(jsonSource);
		writer.close();
	}
}
