package betterWeather;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;


public class CallWeatherAPI {
	private static HttpURLConnection connection;
	private String apiKey = "947715afc3c347e432a3c4fa32ae1af3";
	
	/**
	 * 
	 * @param cityName, the city name to search weather data through api call
	 */
	public void makeApiCall(String cityName) {
		//Create client to make api call to openAPI with a city name a user has inputted
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey + "&units=imperial")).build();
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
			.thenApply(HttpResponse::body)
			.thenApply(CallWeatherAPI::parse)
			.join();
	}
	
	/**
	 * 
	 * @param responseBody, The response from our api call made directly in "makeApiCall"
	 * @return, returns null, the purpose of this function is to just print out the weather information from the response body
	 */
	private static String parse(String responseBody) {
		// tries to parse API data that was called in makeApiCall, returns any errors
		try {
			JSONObject data = new JSONObject(responseBody);
			JSONObject main = data.getJSONObject("main");
			
			String cityName = data.getString("name");
			double tempInF = main.getDouble("temp");
	
			System.out.println("City: " + cityName + ", Current temp is " + tempInF +"°F");
		} catch (JSONException e) {
			System.out.print("Error, input a valid city. If spaces in between please place %20 for each space. EX: New York -> New%20York");
		}
		
		return null;
	}
}
