package betterWeather;

import java.awt.List;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CallWeatherAPI {
	private static HttpURLConnection connection;
	private static String apiKey = "947715afc3c347e432a3c4fa32ae1af3";
	
	/**
	 * 
	 * @param cityName, the city name to search weather data through api call
	 */
	public void makeCityApiCall(String cityName) {
		//Create client to make api call to openAPI with a city name a user has inputted
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey + "&units=imperial")).build();
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
			.thenApply(HttpResponse::body)
			.thenApply(CallWeatherAPI::parseCity)
			.join();
	}
	
	
	/**
	 * 
	 * @param responseBody, The response from our api call made directly in "makeCityApiCall"
	 * @return, returns null, the purpose of this function is to just utilize the weather information from the response body
	 */
	private static String parseCity(String responseBody) {
		// tries to parse API data that was called in makeApiCall, returns any errors
		try {
			JSONObject data = new JSONObject(responseBody);
			JSONObject main = data.getJSONObject("main");
			
			String cityName = data.getString("name");
			double tempInF = main.getDouble("temp");
			
			System.out.println("City: " + cityName);
			System.out.println("Current day temp: " + tempInF + "\u00B0" + "F"); 
			
			JSONObject coord = data.getJSONObject("coord");
			Double lat = coord.getDouble("lat");
			Double lon = coord.getDouble("lon");
			System.out.println("Coordinates -> Latitude: " + lat + " / Longitude: " + lon);
			makeCoordApiCall(lat, lon);
		} catch (JSONException e) {
			System.out.print("Error, input a valid city. If spaces in between please place %20 for each space. EX: New York -> New%20York");
		}
		return null;
	}
	
	/**
	 * 
	 * @param lat The latitude of the city, passed from parseCity
	 * @param lon The longitude of the city, passed from parseCity
	 */
	public static void makeCoordApiCall(double lat, double lon) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon
						+ "&exclude=minutely" + "&appid=" + apiKey + "&units=imperial")).build();
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
			.thenApply(HttpResponse::body)
			.thenApply(CallWeatherAPI::parseCoord)
			.join();		
	}
	
	/**
	 * 
	 * @param responseBody The response from our api call made directly in "makeCoordApiCall"
	 * @return returns null, the purpose of this function is to just print out the weather information from the response body
	 */
	private static String parseCoord(String responseBody) {
		try {
			JSONObject data = new JSONObject(responseBody);
			JSONArray hourly_48hours = data.getJSONArray("hourly");
			JSONArray daily_7days = data.getJSONArray("daily");
			
			System.out.println("24 Hour / Hourly Report: ");
			for (int i = 0 ; i < hourly_48hours.length() ; i++) {
				JSONObject hourlyReport = (JSONObject) hourly_48hours.get(i);
				double tempInF = hourlyReport.getDouble("temp");
				if (i < 25) {
					System.out.println("Hour " + i + " temp: "  + tempInF + "\u00B0" +"F");
				}
			}
			
			System.out.println("7 Day / Daily Report: ");
			for (int i = 0 ; i < daily_7days.length() ; i++) {
				JSONObject dailyReport = (JSONObject) daily_7days.get(i);
				JSONObject tempObj = (JSONObject) dailyReport.getJSONObject("temp");
				double dayTempInF = tempObj.getDouble("day");
				double lowTempInF = tempObj.getDouble("min");
				double highTempInF = tempObj.getDouble("max");
				System.out.println("Day " + i + "-> Temp: " + dayTempInF + "\u00B0" + "F   " + 
				"Low: " + lowTempInF + "\u00B0" + "F   "  + "High: " + highTempInF + "\u00B0" + "F");
			}
		} catch (JSONException e) {
			System.out.print("Error in second api call");		}
		return null;
	}
}
