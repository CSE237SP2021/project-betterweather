package betterWeather;

public class Formatter {
	
	public Formatter() {}
	
	public String formatCity(String city) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < city.length(); ++i) {
			if(city.charAt(i) == ' ') {
				sb.append("%20");
			} else {
				sb.append(city.charAt(i));
			}
		}
		return sb.toString();
	}
	
	public static String capitalizeFirstLetter(String str) {
		if(str == null) return str;
	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public static int getFirstDigit(int number) {
		return Integer.parseInt(Integer.toString(number).substring(0, 1));
	}
	
	public static void CreateArt(int id) {
		int firstDigit = getFirstDigit(id);
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
	
}
