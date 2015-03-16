package softarch.portal.db.json;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import org.json.*;

import softarch.portal.data.FreeSubscription;

public class Test {
	public static void main(String[] args) throws IOException, JSONException {
		FreeSubscription user1 = new FreeSubscription("arosette", "arosette", "Arnaud",
				"Rosette", "arosette@soft.be", new Date());
		FreeSubscription user2 = new FreeSubscription("test", "test", "Arnaud",
				"Rosette", "arosette@soft.be", new Date());
		JSONObject json = XML.toJSONObject(user1.asXml()+user2.asXml());

		FileOutputStream out = new FileOutputStream("/home/arnaud/test.json");
		out.write(json.toString().getBytes());
		
		String jsSource = new Scanner(new FileInputStream("/home/arnaud/test.json")).useDelimiter("\\Z").next();
		JSONObject jsonRead = new JSONObject(jsSource);
		JSONArray obj = jsonRead.getJSONArray("FreeSubscription");
		System.out.println(obj.getJSONObject(0).get("username"));
	}
}
