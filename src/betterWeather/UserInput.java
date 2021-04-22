package betterWeather;

import java.util.Scanner;

public class UserInput {
	
	private Scanner keybordIn;
	public boolean isCityName;
	
	public UserInput() {
		this.keybordIn = new Scanner(System.in);
		this.isCityName = true;
	}
	
	public String askForCity() {
		System.out.println("Please input the name of a city");
		String cityName = this.keybordIn.nextLine();
		return cityName;
	}
	
	public String askForZip() {
		System.out.println("Please input the zipcode of a city");
		String cityZip = this.keybordIn.nextLine();
		return cityZip;
	}
	
	public String askCityOrZip() {
		System.out.println("Search city by 'name' or 'zipcode'");
		String input = this.keybordIn.nextLine();
		String result = null;
		Formatter formatter = new Formatter();
		if (validateCity(input)) {
			result = askForCity();
			result = formatter.formatCity(result);
		}
		else if (validateZip(input)) {
			result = askForZip();
			this.isCityName = false;
		}
		else {
			System.out.println("Please type 'name' or 'zipcode' to specify your search method.");
			result = askCityOrZip();
		}
		return result;
	}
	
	public boolean validateZip(String input) {
		if(input.toLowerCase().equals("zipcode")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validateCity(String input) {
		if(input.toLowerCase().equals("name")) {
			return true;
		}
		return false;
	}
	
	public String hourlyOrDaily() {
		System.out.println("Do you want an 'hourly' or 'daily' report?");
		String input = this.keybordIn.nextLine();
		if((!(validateHourly(input) || validateDaily(input)))) {
			input = hourlyOrDaily();
		}
		return input;
	}
	
	public boolean validateHourly(String input) {
		if(input.toLowerCase().equals("hourly")) {
			return true;
		}
		return false;
	}
	
	public boolean validateDaily(String input) {
		if(input.toLowerCase().equals("daily")) {
			return true;
		}
		return false;
	}
	
	public String oneOrAll_dailyReport() {
		System.out.println("Do you want to display 'one' day or 'all' days this week?");
		String input = this.keybordIn.nextLine();
		if (!(validateOne(input) || validateAll(input))) {
			input = oneOrAll_dailyReport();
		}
		return input;
	}
	
	public boolean validateOne(String input) {
		if(input.toLowerCase().equals("one")) {
			return true;
		}
		return false;
	}
	
	public boolean validateAll(String input) {
		if(input.toLowerCase().equals("all")) {
			return true;
		}
		return false;
	}
	
	public String whichDay_dailyReport() {
		System.out.println("How many days ahead do you want to see ('0', '1', '2', '3', '4', '5', '6', '7'): ");
		String input = this.keybordIn.nextLine();
		if (!(validateWhichDay(input))) {
			input = whichDay_dailyReport();
		}
		return input;
	}
	
	public boolean validateWhichDay(String input) {
		if(input.equals("0") || 
				input.equals("1") ||
				input.equals("2") || 
				input.equals("3") || 
				input.equals("4") || 
				input.equals("5") || 
				input.equals("6") || 
				input.equals("7")) 
		{
			return true;
		}
		return false;
	}
	
	public String oneOrAll_hourlyReport() {
		System.out.println("Do you want to display 'one' specific hour or 'all' 24 hours?");
		String input = this.keybordIn.nextLine();
		if (!(validateOne(input) || validateAll(input))) {
			input = oneOrAll_hourlyReport();
		}
		return input;
	}
	
	public String whichHour_hourlyReport() {
		System.out.println("How many hours ahead do you want to see ('0', '1', '2', ... , '24'): ");
		String input = this.keybordIn.nextLine();
		if (!(validateWhichHour(input))) {
			input = whichHour_hourlyReport();
		}
		return input;
	}
	
	public boolean validateWhichHour(String input) {
		if(input.equals("0") || 
				input.equals("1") ||
				input.equals("2") || 
				input.equals("3") || 
				input.equals("4") || 
				input.equals("5") || 
				input.equals("6") || 
				input.equals("7") || 
				input.equals("8") || 
				input.equals("9") || 
				input.equals("10") || 
				input.equals("11") || 
				input.equals("12") || 
				input.equals("13") || 
				input.equals("14") || 
				input.equals("15") || 
				input.equals("16") || 
				input.equals("17") || 
				input.equals("18") || 
				input.equals("19") || 
				input.equals("20") || 
				input.equals("21") || 
				input.equals("22") || 
				input.equals("23") || 
				input.equals("24"))
		{
			return true;
		}
		return false;
	}
	
	public boolean askRunAgain() {
		System.out.println("What would you like to do next? (number or command)");
		System.out.println("1. Main menu");
		System.out.println("2. Quit");
		String input = this.keybordIn.nextLine();
		if(validateMainMenu(input)) {
			return true;
		} else if (validateQuit(input)) {
			System.out.println("Thank you for using Better Weather!");
			return false;
		}else {
			System.out.println(input +" is not a valid option. Please try again");
			askRunAgain();
		}
		return true;
	}
	
	public boolean validateMainMenu(String input) {
		if(input.equals("1")||input.toLowerCase().equals("main menu")) {
			return true;
		}
		return false;
	}
	
	public boolean validateQuit(String input) {
		if(input.equals("2")||input.toLowerCase().equals("quit")) {
			return true;
		}
		return false;
	}
	
	public double[] askForCoordinates() {
		double[] coordinates = {Double.MAX_VALUE, Double.MAX_VALUE};
		double invalidCoordinatesCounter = 0;
		while(!this.validateCoordinates(coordinates)) {
			if(invalidCoordinatesCounter > 0) {
				System.out.println("Please input valid coordinates");
			}
			System.out.println("Please input the lattitude (-90 to 90)");
			String lattitude = this.keybordIn.nextLine();
			coordinates[0]=Double.parseDouble(lattitude);
			System.out.println("Please input the longitude (-180 to 180)");
			String longitude = this.keybordIn.nextLine();
			coordinates[1]=Double.parseDouble(longitude);
			invalidCoordinatesCounter++;
		}
		return coordinates;
	}
	
	public boolean validateCoordinates(double[] coordinates) {
		boolean validCoordinates = true;
		if(coordinates[0] < -90 || coordinates[0] > 90) {
			validCoordinates = false;
		}
		if(coordinates[1] < -180 || coordinates[1] > 180) {
			validCoordinates = false;
		}
		return validCoordinates;
	}

}
