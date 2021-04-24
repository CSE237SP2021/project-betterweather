package betterWeather;

import java.text.DecimalFormat;
import java.text.ParseException;

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
	
	public static double roundToTwoDecimals(double number) {
		return Math.round(number * 100.00) / 100.00;
	}
	
	public static String getOutfitImperial(double tempInF) {
		if(tempInF > 90) {
			return "Too hot! sunscreen use is recommended";
		}else if((tempInF >= 70) && (tempInF <= 90)){
			return "Regular summer wear";
		}else if((tempInF >= 50) && (tempInF <= 69)){
			return "Warm hoodie or sweater";
		}else if((tempInF >= 40) && (tempInF <= 49)){
			return "Jacket";
		}else if((tempInF >= 30) && (tempInF <= 39)){
			return "Winter coat, gloves and beanie";
		}else if(tempInF<= 29){
			return "Consider double layer and thermos";
		}
		return "Not available";
	}
	
	public static String getOutfitMetric(double tempInF) {
		if(tempInF > 32) {
			return "Too hot! sunscreen use is recommended";
		}else if((tempInF >= 20) && (tempInF <= 32)){
			return "Regular summer wear";
		}else if((tempInF >= 9) && (tempInF < 20)){
			return "Warm hoodie or sweater";
		}else if((tempInF >= 4) && (tempInF < 9)){
			return "Jacket";
		}else if((tempInF >= -1) && (tempInF < 4)){
			return "Winter coat, gloves and beanie";
		}else if(tempInF < -1 ){
			return "Consider double layer and thermos";
		}
		return "Not available";
	}
	
	public static String getUnit(String unitsAPICall) {
		if(unitsAPICall.equalsIgnoreCase("metric")) {
			return "C";
		}else if (unitsAPICall.equalsIgnoreCase("imperial")){
			return "F";
		}	
		else {
			return "N/A";
		}
	}
}

