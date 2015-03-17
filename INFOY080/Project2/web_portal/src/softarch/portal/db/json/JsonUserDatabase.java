package softarch.portal.db.json;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import softarch.portal.data.FreeSubscription;
import softarch.portal.data.UserProfile;
import softarch.portal.db.DatabaseException;
import softarch.portal.db.UserDatabase;

public class JsonUserDatabase implements UserDatabase {
	
	private JsonFile jsonFile;
	
	private static String[] USER_TYPES = 
		{"FreeSubscription",
		"CheapSubscription",
		"ExpensiveSubscription",
		"Operator",
		"ExternalAdministrator",
		"RegularAdministrator",
		"ExpertAdministrator"};
	
	public JsonUserDatabase(String dbUrl) {
		this.jsonFile = new JsonFile(dbUrl);
	}

	public void insert(UserProfile profile) throws DatabaseException {
		try {
			jsonFile.appendXml(profile.asXml());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void update(UserProfile profile) throws DatabaseException {
		// TODO Auto-generated method stub
	}

	public UserProfile findUser(String username) throws DatabaseException {
		UserProfile user = null;
		
		String jsonSource = "";
		try {
			jsonSource = jsonFile.readFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jsonDatabase = null;
		
		// If the json file is empty, there is no user
		if (!jsonSource.isEmpty()) {
			try {
				jsonDatabase = new JSONObject(jsonSource);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			JSONObject jsonEntry = null;
			Boolean found = false;
			
			for (int i=0; i<USER_TYPES.length && !found; ++i) {
				String tableName = USER_TYPES[i];
				
				try {
					JSONArray jsonTable = jsonDatabase.getJSONArray(tableName);
					for (int j=0; j<jsonTable.length() && !found; ++j) {
						try {
							jsonEntry = jsonTable.getJSONObject(j);
						} catch (JSONException e) {
							e.printStackTrace();
						}
						
						String usernameFromDatabase = "";
						try {
							usernameFromDatabase = jsonEntry.getString("username");
						} catch (JSONException e) {
							e.printStackTrace();
						}
						if (username.equals(usernameFromDatabase)) {
							found = true;
							
						}
					}
				}
				// This case is entered iff the table only contains one entry 
				catch (JSONException e) {
					try {
						jsonEntry = jsonDatabase.getJSONObject(tableName);
						if (username.equals(jsonEntry.getString("username"))) {
							found = true;
						}
					} catch (JSONException e1) {
						// Do nothing
					}
				}
				
				if (found) {
					String usernameFromDatabase = "";
					String password = "";
					String firstName = "";
					String lastName = "";
					String emailAddress = "";
					Date lastLogin = null;
					try {
						usernameFromDatabase = jsonEntry.getString("username");
						password = jsonEntry.getString("password");
						firstName = jsonEntry.getString("firstName");
						lastName = jsonEntry.getString("lastName");
						emailAddress = jsonEntry.getString("emailAddress");
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						lastLogin = df.parse(jsonEntry.getString("lastLogin"));
					} catch (JSONException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					user = new FreeSubscription(usernameFromDatabase, password, firstName, lastName, emailAddress, lastLogin);
				}
			}
		}
		
		if (user==null) {
			throw new DatabaseException("Invalid username!");
		}

		return user;
	}

	public boolean userExists(String username) throws DatabaseException {
		Boolean answer = true;
		try {
			findUser(username);
		}
		catch (DatabaseException e) {
			if (e.getMessage().equals("Invalid username!")) {
				answer = false;
			}
		}
		return answer;
	}

}
