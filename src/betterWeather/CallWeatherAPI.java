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

import betterWeather.Formatter;
import betterWeather.UserInput;


public class CallWeatherAPI {
	private static HttpURLConnection connection;
	private static String apiKey = "947715afc3c347e432a3c4fa32ae1af3";
	
	/**
	 * 
	 * @param cityName, the city name to search weather data through api call
	 */
	public void makeCityApiCall(String cityName_or_Zip, boolean isCityName) {
		//Create client to make api call to openAPI with a city name a user has inputted
		HttpClient client = HttpClient.newHttpClient();
		String url;
		if (isCityName) {
			url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName_or_Zip + "&appid=" + apiKey + "&units=imperial";
		}
		else {
			url = "https://api.openweathermap.org/data/2.5/weather?zip=" + cityName_or_Zip + "&appid=" + apiKey + "&units=imperial";
		}
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url)).build();
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
			System.out.print("Invalid city.");
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
	 * @return null, purpose of the function is to print out the weather
	 */
	private static String parseCoord(String responseBody) {
		try {
			JSONObject data = new JSONObject(responseBody);
			UserInput input = new UserInput();
			String hourlyOrDaily = input.hourlyOrDaily();
			if(hourlyOrDaily.equalsIgnoreCase("hourly")) {
				JSONArray hourly_48hours = data.getJSONArray("hourly");
				
				UserInput uInput = new UserInput();
				String thisHour = null;
				String allOrOneHour = uInput.oneOrAll_hourlyReport();
				
				//if user only wants to see one specific hour
				if (allOrOneHour.equalsIgnoreCase("one")) {
					thisHour = uInput.whichHour_hourlyReport();
					JSONObject hourlyReport = (JSONObject) hourly_48hours.get(Integer.parseInt(thisHour));
					Integer dt = hourlyReport.getInt("dt");
					Date dt2 = new Date(dt * 1000L);
					SimpleDateFormat sfd = new SimpleDateFormat("h:mm a");
					double tempInF = hourlyReport.getDouble("temp");
					System.out.println(sfd.format(dt2) + " temp: "  + tempInF + "\u00B0" +"F");
				}
				//user wants to print all 24 hours ahead
				else {
					System.out.println("24 Hour / Hourly Report: ");
					for (int i = 0 ; i < hourly_48hours.length() ; i++) {
						JSONObject hourlyReport = (JSONObject) hourly_48hours.get(i);
						Integer dt = hourlyReport.getInt("dt");
						Date dt2 = new Date(dt * 1000L);
						SimpleDateFormat sfd = new SimpleDateFormat("h:mm a");
						double tempInF = hourlyReport.getDouble("temp");
						if (i < 25) {
							System.out.println(sfd.format(dt2) + " temp: "  + tempInF + "\u00B0" +"F");
						}
					}
				}

			} else {
				JSONArray daily_7days = data.getJSONArray("daily");
				UserInput uInput = new UserInput();
				String thisDay = null;
				String allOrOne = uInput.oneOrAll_dailyReport();
				
				if (allOrOne.equalsIgnoreCase("one")) {
					thisDay = uInput.whichDay_dailyReport();
				}
				
				//7 Day report
				System.out.println("7 Day / Daily Report: ");
				for (int i = 0 ; i < daily_7days.length() ; i++) {
					//Single day report
					if (allOrOne.equalsIgnoreCase("one")) {
						if(i == Integer.parseInt(thisDay)) {
							generateDailyReport(daily_7days, i);
							break;
						}
						continue;
					}
					generateDailyReport(daily_7days, i);
				}
			}
		} catch (JSONException e) {
			System.out.print("Error in second api call");
		}
		return null;
	}
	
	private static void generateDailyReport(JSONArray thisWeek, int index) {
		JSONObject dailyReport;
		try {
			dailyReport = (JSONObject) thisWeek.get(index);
			JSONObject tempObj = (JSONObject) dailyReport.getJSONObject("temp");
			double dayTempInF = tempObj.getDouble("day");
			double lowTempInF = tempObj.getDouble("min");
			double highTempInF = tempObj.getDouble("max");
			Integer dt = dailyReport.getInt("dt");
			Date dt2 = new Date (dt*1000L); 
			double precipitation = Formatter.roundToTwoDecimals(dailyReport.getDouble("pop") * 100.00);
			double cloudiness = Formatter.roundToTwoDecimals(dailyReport.getDouble("clouds"));
			JSONArray weatherArray = dailyReport.getJSONArray("weather");
			String description = weatherArray.optJSONObject(0).getString("description");
			SimpleDateFormat sfd = new SimpleDateFormat("MMM-dd-yyyy");
			System.out.println("Day " + sfd.format(dt2));
			System.out.println("        " + Formatter.capitalizeFirstLetter(description));
			Formatter.CreateArt(weatherArray.optJSONObject(0).getInt("id"));
			System.out.println("        Temp: " + dayTempInF + "\u00B0" + "F");
			System.out.println("        Low: " + lowTempInF + "\u00B0" + "F");
			System.out.println("        High: " + highTempInF + "\u00B0" + "F");
			System.out.println("        Precipitation: " + precipitation + "%");
			System.out.println("        Cloudiness: " + cloudiness + "%");
		} catch (JSONException e) {
			System.out.print("Error generating daily report");
		}
	}
}
