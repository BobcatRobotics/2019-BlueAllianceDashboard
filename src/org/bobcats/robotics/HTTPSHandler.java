package org.bobcats.robotics;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/*
 * Calling TBA
 */

public class HTTPSHandler {

	private final static String key = "X-TBA-Auth-Key";
	private final static String apisecret = "IJkM2WwIV6qhAuZ5IAP3YYAfHJPfposl9vx2SXI3iDtlQzuK3dlQ8MxTpk10Mk6J";

	public HTTPSHandler() {
		super();
	}

	public <T> T callURL(Class<T> type, String url) throws IOException {
		URL myUrl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) myUrl.openConnection();
		setHeaders(conn);

		// String baseAuthStr = key + ":" + apisecret;
		// conn.addRequestProperty("Authorization", "Basic " + baseAuthStr);
		// conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;
		// charset=UTF-8");
		T jsonObject = null;
		try (Reader reader = new InputStreamReader(conn.getInputStream(), "UTF-8")) {

			// InputStream is = conn.getInputStream();
			// InputStreamReader isr = new InputStreamReader(is);
			// BufferedReader br = new BufferedReader(isr);
			//
			// String inputLine;
			//
			// while ((inputLine = br.readLine()) != null) {
			// // System.out.println(inputLine);
			// }
			//
			// br.close();

			Gson gson = new GsonBuilder().create();
			jsonObject = gson.fromJson(reader, type);
			//System.out.println(jsonObject.toString());
		}
		return jsonObject;
	}

	public <T> T callURL(Class<T> type, String url, String... args) throws IOException {
		String callURL = replaceArgs(url, args);
		URL myUrl = new URL(callURL);
		HttpURLConnection conn = (HttpURLConnection) myUrl.openConnection();
		setHeaders(conn);

		T jsonObject = null;
		try (Reader reader = new InputStreamReader(conn.getInputStream(), "UTF-8")) {
			Gson gson = new GsonBuilder().create();
			jsonObject = gson.fromJson(reader, type);
			//System.out.println(jsonObject.toString());
		}
		return jsonObject;
	}

	public <T> List<T> callListURL(Class<T> type, String url, String... args) throws IOException {
		String callURL = replaceArgs(url, args);
		System.out.println("CallURL - " + callURL);
		URL myUrl = new URL(callURL);
		HttpURLConnection conn = (HttpURLConnection) myUrl.openConnection();
		setHeaders(conn);

		List<T> jsonList = new ArrayList<T>();
		// T jsonObject = null;
		try (Reader reader = new InputStreamReader(conn.getInputStream(), "UTF-8")) {
			// use string builder to avoid unnecessary string creation.
			StringBuilder builder = new StringBuilder();
			int charsRead = -1;
			char[] chars = new char[100];
			do {
				charsRead = reader.read(chars, 0, chars.length);
				// if we have valid chars, append them to end of string.
				if (charsRead > 0)
					builder.append(chars, 0, charsRead);
			} while (charsRead > 0);
			String stringReadFromReader = builder.toString().replaceAll("\\s+"," ");
			
			//System.out.println("String read = !!!!![" + stringReadFromReader + "]!!!!!!");
			Gson gson = new GsonBuilder().setLenient().create();
			//Gson gson = new GsonBuilder().create();
			int startPos = stringReadFromReader.indexOf('{');
			while (startPos > 0) {
				String cleanjsonStrObj = extractJSON(stringReadFromReader, startPos);
				if (cleanjsonStrObj.length() > 0) {
					try {
						T obj = gson.fromJson(cleanjsonStrObj, type);
						jsonList.add(gson.fromJson(cleanjsonStrObj, type));
					} catch (JsonSyntaxException e) {
						System.err.println("Error processing " + cleanjsonStrObj);
						System.err.println(e);
					}
				}
				startPos = stringReadFromReader.indexOf('{', startPos + cleanjsonStrObj.length() + 1);
			}

			// JsonParser jsonParser = new JsonParser();
			// // JsonObject jo = (JsonObject)jsonParser.parse(stringReadFromReader);
			// JsonArray jsonArr = (JsonArray) jsonParser.parse(stringReadFromReader);
			// // System.out.println("!!!!jsonArr is : "+jsonArr);
			//
			// Gson json = new Gson();
			// List<LinkedTreeMap<String, String>> jsonStrList = (List<LinkedTreeMap<String,
			// String>>) json
			// .fromJson(jsonArr, ArrayList.class);
			// System.out.println("List size is : " + jsonStrList.size());
			// // System.out.println("List Elements are : "+jsonStrList.toString());
			// for (int i = 0; i < jsonStrList.size(); i++) {
			// String x = jsonStrList.get(i).toString();
			// //System.out.println(x);
			//// String cleanjsonStrObj = replaceSpaceValues(jsonStrList.get(i).toString());
			//// if (cleanjsonStrObj.contains("USA")) {
			////
			//// System.out.println("Event " + cleanjsonStrObj);
			//// T obj = json.fromJson(cleanjsonStrObj, type);
			//// jsonList.add(json.fromJson(cleanjsonStrObj, type));
			//// }
			// }
		}
		return jsonList;
	}

	private String replaceArgs(String url, String... args) {
		String callURL = url;
		// Replace URL with http params
		int argNbr = 1;
		for (String arg : args) {
			String argStr = "{" + argNbr + "}";
			if (callURL.contains(argStr)) {
				String replacedStr = callURL.replace(argStr, arg);
				callURL = replacedStr;
			}
			argNbr++;
		}
		//System.out.println("!!!! New URL is " + callURL);
		return callURL;

	}

	private void setHeaders(HttpURLConnection conn) {
		conn.setUseCaches(false);
		conn.setRequestProperty("User-Agent", "MyAgent");
		conn.setConnectTimeout(30000);
		conn.setReadTimeout(30000);
		conn.setRequestProperty(key, apisecret);

	}

	private String replaceSpaceValues(String json) {
		// System.out.println("original string " + json);
		StringBuilder sb = new StringBuilder();
		int from = 0;
		int to = json.indexOf('=', from);
		while (to > 0) {
			sb.append(json.substring(from, to + 1));
			int comma = json.indexOf(',', to + 1);
			if (comma == -1) {
				sb.append(json.substring(to + 1));
				break;
			}
			String subStr = json.substring(to + 1, comma);
			if (subStr.contains("{")) {
				sb.append("{");
				from = to + 2;
				to = json.indexOf('=', from);
				continue;
			}
			if (subStr.contains(" ")) {
				sb.append("\"");
				sb.append(subStr);
				sb.append("\"");
			} else {
				sb.append(subStr);
			}
			from = comma;
			to = json.indexOf('=', from);
		}
		// System.out.println("returning newstring " + sb.toString());
		return sb.toString();
	}

	private String extractJSON(String json, int startingPos) {
		// System.out.println("original string " + json);
		int pos = startingPos + 1;
		int nbrLeft = 1;
		while (nbrLeft > 0 && pos < json.length()) {
			if ('{' == json.charAt(pos)) 
				nbrLeft++;
			if ('}' == json.charAt(pos))
				nbrLeft--;
			pos++;
		}
		String jsonExtract = "";
		if (pos < json.length()) {
			jsonExtract = json.substring(startingPos, pos);
		}
		///System.out.println("extractJSON() " + jsonExtract);
		return jsonExtract;
	}
}
