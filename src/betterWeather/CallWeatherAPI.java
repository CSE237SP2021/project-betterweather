package betterWeather;

import java.awt.List;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
			System.out.println("Error, input a valid city. If spaces in between please place %20 for each space. EX: New York -> New%20York");
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
		System.out.println("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon
						+ "&exclude=minutely" + "&appid=" + apiKey + "&units=imperial");
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
				Integer dt = dailyReport.getInt("dt");
				Date dt2 = new Date (dt); 
				double precipitation = dailyReport.getDouble("pop") * 100.00;
				double cloudiness = dailyReport.getDouble("clouds");
				JSONArray weatherArray = dailyReport.getJSONArray("weather");
				String description = weatherArray.optJSONObject(0).getString("description");
				SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
				System.out.println("Day " + sfd.format(dt2));
				System.out.println("        " + capitalizeFirstLetter(description));
				CreateArt(weatherArray.optJSONObject(0).getInt("id"));
				System.out.println("        Temp: " + dayTempInF + "\u00B0" + "F");
				System.out.println("        Low: " + lowTempInF + "\u00B0" + "F");
				System.out.println("        High: " + highTempInF + "\u00B0" + "F");
				System.out.println("        Precipitation: " + precipitation + "%");
				System.out.println("        Cloudiness: " + cloudiness + "%");
			}
		} catch (JSONException e) {
			System.out.print("Error in second api call");
		}
		return null;
	}
	
	
	private static void CreateArt(int id) {
		int firstDigit = Integer.parseInt(Integer.toString(id).substring(0, 1));
		//Thunderstorm
		if(firstDigit == 2) {
			System.out.println("                      _____ ");
			System.out.println("                     /     \\ ");
			System.out.println("                     \\_____/ ");
			System.out.println("                     '  /  '");
			System.out.println("                      ' - '");
			System.out.println("                     '  /  '");
		}
		//Drizzle
		if(firstDigit == 3) {
			System.out.println("                      _____ ");
			System.out.println("                     /     \\ ");
			System.out.println("                     \\_____/ ");
			System.out.println("                         '");
			System.out.println("                        '");
			System.out.println("                          '");
		}
		//Rain
		if(firstDigit == 5) {
			System.out.println("                      _____ ");
			System.out.println("                     /     \\ ");
			System.out.println("                     \\_____/ ");
			System.out.println("                     '  '  '");
			System.out.println("                      ' ' '");
			System.out.println("                     '   '  '");
		}
		//Snow
		if(firstDigit == 6) {
			System.out.println("                      _____ ");
			System.out.println("                     /     \\ ");
			System.out.println("                     \\_____/ ");
			System.out.println("                        *      ");
			System.out.println("                      *    *     ");
			System.out.println("                       *  *       ");
		}
		//Clear
		if(firstDigit == 7 || id == 800) {
			System.out.println("                       |  ");
			System.out.println("                      _|_ ");
			System.out.println("                 ____/   \\____ ");
			System.out.println("                     \\___/ ");
			System.out.println("                       |  ");
			System.out.println("                       |  ");
		}
		//Cloudy
		if(firstDigit == 8 && id!= 800) {
			System.out.println("                      _____ ");
			System.out.println("                     /     \\ ");
			System.out.println("                     \\_____/ ");
			System.out.println("                 _____       _____   ");
			System.out.println("                /     \\     /     \\ ");
			System.out.println("                \\_____/     \\_____/ ");
		}
	}
	
	private static String capitalizeFirstLetter(String str) {
		if(str == null) return str;
	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}
