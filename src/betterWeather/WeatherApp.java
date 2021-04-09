package betterWeather;

public class WeatherApp {

	public static void main(String[] args) {
		UserInput input = new UserInput();
		String searchInput = input.askCityOrZip();
		CallWeatherAPI apiCalls = new CallWeatherAPI();
		apiCalls.makeCityApiCall(searchInput, input.isCityName);

		
	}
}
