package betterWeather;

public class FormatCity {

	String city;
	
	public FormatCity(String city) {
		this.city = city;
	}
	
	public String formatCity() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.city.length(); ++i) {
			if(this.city.charAt(i) == ' ') {
				sb.append("%20");
			} else {
				sb.append(this.city.charAt(i));
			}
		}
		return sb.toString();
	}
}
