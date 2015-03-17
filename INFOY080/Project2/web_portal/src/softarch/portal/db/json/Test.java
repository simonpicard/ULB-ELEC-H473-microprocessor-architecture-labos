package softarch.portal.db.json;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Scanner;

import org.json.*;

import softarch.portal.app.ApplicationFacade;
import softarch.portal.data.FreeSubscription;
import softarch.portal.db.DatabaseException;
import softarch.portal.db.DatabaseFacade;

public class Test {
	public static void main(String[] args) throws IOException, JSONException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, DatabaseException {
		FreeSubscription user1 = new FreeSubscription("arosette", "arosette", "Arnaud",
				"Rosette", "arosette@soft.be", new Date());
		FreeSubscription user2 = new FreeSubscription("test", "test", "Arnaud",
				"Rosette", "arosette@soft.be", new Date());
		//JSONObject json = XML.toJSONObject(user1.asXml()+user2.asXml());

		//FileOutputStream out = new FileOutputStream("/home/arnaud/test.json");
		//out.write(json.toString().getBytes());
		
		//String jsSource = new Scanner(new FileInputStream("/home/arnaud/test.json")).useDelimiter("\\Z").next();
		//JSONObject jsonRead = new JSONObject(jsSource);
		//JSONArray obj = jsonRead.getJSONArray("FreeSubscription");
		//System.out.println(obj.getJSONObject(0).get("username"));
		
		String dbUser = "SA";
		String dbPassword = "";
		String dbUrl = "/home/arnaud/test.json";
		String dbType = "JSON";
		DatabaseFacade database = new DatabaseFacade(dbUser, dbPassword, dbUrl, dbType);
		database.insert(user1);
		database.insert(user2);
	}
}
