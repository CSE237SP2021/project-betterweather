package betterWeather;

public class WeatherApp {

	public static void main(String[] args) {
		Boolean runAgain = true;
		while(runAgain){
			UserInput input = new UserInput();
			String searchInput = input.askCityOrZip();
			CallWeatherAPI apiCalls = new CallWeatherAPI();
			apiCalls.makeCityApiCall(searchInput, input.isCityName);
			runAgain = input.askRunAgain();
		}
	}
}
