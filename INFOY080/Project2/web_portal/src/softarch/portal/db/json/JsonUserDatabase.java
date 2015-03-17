package softarch.portal.db.json;

import java.io.IOException;
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
		JSONObject jsonObject = null;
		if (!"".equals(jsonSource)) {
			try {
				jsonObject = new JSONObject(jsonSource);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			JSONArray freeSubscriptions = null;
			try {
				freeSubscriptions = jsonObject.getJSONArray("FreeSubscription");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			Boolean found = false;
			
			for (int i=0; i<freeSubscriptions.length() && !found; ++i) {
				JSONObject freeSubscription = null;
				try {
					freeSubscription = freeSubscriptions.getJSONObject(i);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				String usernameFromDatabase = "";
				try {
					usernameFromDatabase = freeSubscription.getString("username");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				if (username.equals(usernameFromDatabase)) {
					found = true;
					String password = "";
					String firstName = "";
					String lastName = "";
					String emailAddress = "";
					Date lastLogin = null;
					try {
						password = freeSubscription.getString("password");
						firstName = freeSubscription.getString("firstName");
						lastName = freeSubscription.getString("lastName");
						emailAddress = freeSubscription.getString("emailAddress");
						lastLogin = new Date(freeSubscription.getString("lastLogin"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					user = new FreeSubscription(usernameFromDatabase, password, firstName, lastName, emailAddress, lastLogin);
				}
			}
		}
		
		return user;
	}

	public boolean userExists(String username) throws DatabaseException {
		Boolean answer = true;
		if (findUser(username) == null) {
			answer = false;
		}
		return answer;
	}

}
