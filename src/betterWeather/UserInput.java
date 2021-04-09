package betterWeather;

import java.util.Scanner;

public class UserInput {
	
	private Scanner keybordIn;
	
	public UserInput() {
		this.keybordIn = new Scanner(System.in);
	}
	
	public String askForCity() {
		System.out.println("Please input the name of a city");
		String cityName = this.keybordIn.nextLine();
		return cityName;
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
