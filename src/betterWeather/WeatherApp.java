package betterWeather;

public class WeatherApp {

	public static void main(String[] args) {
		Boolean runAgain = true;
		while(runAgain){
			UserInput input = new UserInput();
			String city = input.askForCity();
			FormatCity formatter = new FormatCity(city);
			String formattedCity = formatter.formatCity();
			CallWeatherAPI apiCalls = new CallWeatherAPI();
			apiCalls.makeCityApiCall(formattedCity);
			runAgain = input.askRunAgain();
		}
	}
}
