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
	
	@Test
	void dailyReportIsOne() {
		UserInput test = new UserInput();
		String one1 = "one";
		String one2 = "oNe";
		String one3 = "ONE";
		assertTrue(test.validateOne(one1));
		assertTrue(test.validateOne(one2));
		assertTrue(test.validateOne(one3));
	}
	
	@Test
	void dailyReportIsNotOne() {
		UserInput test = new UserInput();
		String one1 = "onee";
		String one2 = "o";
		String one3 = "O";
		assertFalse(test.validateOne(one1));
		assertFalse(test.validateOne(one2));
		assertFalse(test.validateOne(one3));
	}
	
	@Test
	void dailyReportIsAll() {
		UserInput test = new UserInput();
		String all1 = "all";
		String all2 = "aLL";
		String all3 = "ALL";
		assertTrue(test.validateAll(all1));
		assertTrue(test.validateAll(all2));
		assertTrue(test.validateAll(all3));
	}
	
	@Test
	void dailyReportIsNotAll() {
		UserInput test = new UserInput();
		String all1 = "alll";
		String all2 = "a11";
		String all3 = "A1L";
		assertFalse(test.validateAll(all1));
		assertFalse(test.validateAll(all2));
		assertFalse(test.validateAll(all3));
	}
	
	@Test
	void dailyReportOneDayTrue() {
		UserInput test = new UserInput();
		String day0 = "0";
		String day1 = "1";
		String day2 = "2";
		String day3 = "3";
		String day4 = "4";
		String day5 = "5";
		String day6 = "6";
		String day7 = "7";
		assertTrue(test.validateWhichDay(day0));
		assertTrue(test.validateWhichDay(day1));
		assertTrue(test.validateWhichDay(day2));
		assertTrue(test.validateWhichDay(day3));
		assertTrue(test.validateWhichDay(day4));
		assertTrue(test.validateWhichDay(day5));
		assertTrue(test.validateWhichDay(day6));
		assertTrue(test.validateWhichDay(day7));
	}
	
	@Test
	void dailyReportOneDayFalse() {
		UserInput test = new UserInput();
		String day1 = "8";
		String day2 = "-1";
		String day3 = "two";
		assertFalse(test.validateWhichDay(day1));
		assertFalse(test.validateWhichDay(day2));
		assertFalse(test.validateWhichDay(day3));
	}
}
