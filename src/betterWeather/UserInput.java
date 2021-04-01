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
