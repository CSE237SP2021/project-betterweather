package betterWeather;

public class WeatherApp {

	public static void main(String[] args) {
		UserInput input = new UserInput();
		String city = input.askForCity();
		CallWeatherAPI apiCalls = new CallWeatherAPI();
		apiCalls.makeApiCall(city);
	}
}
