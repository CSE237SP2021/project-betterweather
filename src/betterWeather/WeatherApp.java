package betterWeather;

public class WeatherApp {

	public static void main(String[] args) {
		UserInput input = new UserInput();
		String city = input.askForCity();
		FormatCity formatter = new FormatCity(city);
		String formattedCity = formatter.formatCity();
//		input.askForWeatherReport();
		CallWeatherAPI apiCalls = new CallWeatherAPI();
		apiCalls.makeCityApiCall(formattedCity);
	}
}
