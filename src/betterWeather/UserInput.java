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
		if (!(input.equalsIgnoreCase("hourly") || input.equalsIgnoreCase("daily"))) {
			System.out.println("Please type 'name' or 'zipcode' to specify your search method.");
			input = hourlyOrDaily();
		}
		return input;
	}

	public boolean askRunAgain() {
		System.out.println("What would you like to do next? (number or command)");
		System.out.println("1. Main menu");
		System.out.println("2. Quit");
		String answer = this.keybordIn.nextLine();
		if(answer.equals("1")||answer.toLowerCase().equals("main menu")) {
			return true;
		} else if (answer.equals("2")||answer.toLowerCase().equals("quit")) {
			System.out.println("Thank you for using Better Weather!");
			return false;
		}else {
			System.out.println(answer +" is not a valid option. Please try again");
			askRunAgain();
		}
		return true;

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
