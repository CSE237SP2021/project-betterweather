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
	
	@Test
	void isCity() {
		UserInput testingCity = new UserInput();
		boolean city1 = testingCity.validateCity("name");
		boolean city2 = testingCity.validateCity("NAmE");
		assertTrue(city1);
		assertTrue(city2);
	}
	
	@Test
	void isNotCity() {
		UserInput testingCity = new UserInput();
		boolean city1 = testingCity.validateCity("NM");
		boolean city2 = testingCity.validateCity("nae");
		assertFalse(city1);
		assertFalse(city2);
	}
	
	@Test
	void reportIsHourly() {
		UserInput test = new UserInput();
		String hourly1 = "hourly";
		String hourly2 = "HOurlY";
		String hourly3 = "HOURLY";
		assertTrue(test.validateHourly(hourly1));
		assertTrue(test.validateHourly(hourly2));
		assertTrue(test.validateHourly(hourly3));
	}
	
	void reportIsNotHourly() {
		UserInput test = new UserInput();
		String hourly1 = "hour4ly";
		String hourly2 = "HrlY";
		String hourly3 = "H";
		assertFalse(test.validateHourly(hourly1));
		assertFalse(test.validateHourly(hourly2));
		assertFalse(test.validateHourly(hourly3));
	}

	@Test
	void reportIsDaily() {
		UserInput test = new UserInput();
		String daily1 = "daily";
		String daily2 = "DaIly";
		String daily3 = "DAILY";
		assertTrue(test.validateDaily(daily1));
		assertTrue(test.validateDaily(daily2));
		assertTrue(test.validateDaily(daily3));
	}
	
	@Test
	void reportIsNotDaily() {
		UserInput test = new UserInput();
		String daily1 = "dai";
		String daily2 = "D";
		String daily3 = "DAIe4LY";
		assertFalse(test.validateDaily(daily1));
		assertFalse(test.validateDaily(daily2));
		assertFalse(test.validateDaily(daily3));
	}
	



}
