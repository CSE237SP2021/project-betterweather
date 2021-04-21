package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import betterWeather.UserInput;

class UserInputTests {

	@Test
	void coordinatesAreValid() {
		UserInput testingCoordinates = new UserInput();
		double[] coordinates1 = {-90, -180};
		double[] coordinates2 = {90, 180};
		boolean correct1 = testingCoordinates.validateCoordinates(coordinates1);
		boolean correct2 = testingCoordinates.validateCoordinates(coordinates2);
		assertTrue(correct1);
		assertTrue(correct2);
	}
	
	@Test
	void coorindatesAreInvalid() {
		UserInput testingCoordinates = new UserInput();
		double[] coordinates1 = {-91, 0};
		double[] coordinates2 = {91, 0};
		double[] coordinates3 = {0, 181};
		double[] coordinates4 = {0, -181};
		boolean incorrect1 = testingCoordinates.validateCoordinates(coordinates1);
		boolean incorrect2 = testingCoordinates.validateCoordinates(coordinates2);
		boolean incorrect3 = testingCoordinates.validateCoordinates(coordinates3);
		boolean incorrect4 = testingCoordinates.validateCoordinates(coordinates4);
		assertFalse(incorrect1);
		assertFalse(incorrect2);
		assertFalse(incorrect3);
		assertFalse(incorrect4);
	}
	
	@Test
	void isZipcode() {
		UserInput testingZip = new UserInput();
		boolean zip1 = testingZip.validateZip("zipcode");
		boolean zip2 = testingZip.validateZip("ZIPCODE");
		assertTrue(zip1);
		assertTrue(zip2);
	}
	
	@Test
	void isNotZipcode() {
		UserInput testingZip = new UserInput();
		boolean zip1 = testingZip.validateZip("zipcde");
		boolean zip2 = testingZip.validateZip("ZC");
		assertFalse(zip1);
		assertFalse(zip2);
	}

}
