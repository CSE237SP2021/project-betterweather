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
	
	@Test
	void isMainMenu() {
		UserInput testingMainMenu = new UserInput();
		boolean mainMenu1 = testingMainMenu.validateMainMenu("main menu");
		boolean mainMenu2 = testingMainMenu.validateMainMenu("1");
		assertTrue(mainMenu1);
		assertTrue(mainMenu2);
	}
	
	@Test
	void isNotMainMenu() {
		UserInput testingMainMenu = new UserInput();
		boolean mainMenu1 = testingMainMenu.validateMainMenu("maiMenu");
		boolean mainMenu2 = testingMainMenu.validateMainMenu("2");
		assertFalse(mainMenu1);
		assertFalse(mainMenu2);
	}
	
	@Test
	void isQuit() {
		UserInput testingQuit = new UserInput();
		boolean quit1 = testingQuit.validateQuit("quit");
		boolean quit2 = testingQuit.validateQuit("2");
		assertTrue(quit1);
		assertTrue(quit2);
	}
	
	void isNotQuit() {
		UserInput testingQuit = new UserInput();
		boolean quit1 = testingQuit.validateQuit("qu1t");
		boolean quit2 = testingQuit.validateQuit("1");
		assertFalse(quit1);
		assertFalse(quit2);
	}
	
	@Test
	void hourlyReportOneHourTrue() {
		UserInput test = new UserInput();
		assertTrue(test.validateWhichHour("1"));
		assertTrue(test.validateWhichHour("2"));
		assertTrue(test.validateWhichHour("3"));
		assertTrue(test.validateWhichHour("4"));
		assertTrue(test.validateWhichHour("5"));
		assertTrue(test.validateWhichHour("6"));
		assertTrue(test.validateWhichHour("7"));
		assertTrue(test.validateWhichHour("8"));
		assertTrue(test.validateWhichHour("9"));
		assertTrue(test.validateWhichHour("10"));
		assertTrue(test.validateWhichHour("11"));
		assertTrue(test.validateWhichHour("12"));
		assertTrue(test.validateWhichHour("13"));
		assertTrue(test.validateWhichHour("14"));
		assertTrue(test.validateWhichHour("15"));
		assertTrue(test.validateWhichHour("16"));
		assertTrue(test.validateWhichHour("17"));
		assertTrue(test.validateWhichHour("18"));
		assertTrue(test.validateWhichHour("19"));
		assertTrue(test.validateWhichHour("20"));
		assertTrue(test.validateWhichHour("21"));
		assertTrue(test.validateWhichHour("22"));
		assertTrue(test.validateWhichHour("23"));
		assertTrue(test.validateWhichHour("24"));
	}
	
	@Test
	void hourlyReportOneHourFalse() {
		UserInput test = new UserInput();
		assertFalse(test.validateWhichHour("-1"));
		assertFalse(test.validateWhichHour("one"));
		assertFalse(test.validateWhichHour("9pm"));
		assertFalse(test.validateWhichHour("25"));
	}
	
	@Test
	void isMetric() {
		UserInput testingMetric = new UserInput();
		assertTrue(testingMetric.validateMetric("metric"));
		assertTrue(testingMetric.validateMetric("MEtric"));
	}
	
	@Test
	void isNotMetric() {
		UserInput testingMetric = new UserInput();
		assertFalse(testingMetric.validateMetric("m3tric"));
		assertFalse(testingMetric.validateMetric("metrik"));
	}
	
	@Test
	void isImperial() {
		UserInput testingImperial = new UserInput();
		assertTrue(testingImperial.validateImperial("imperial"));
		assertTrue(testingImperial.validateImperial("ImpErial"));
	}
	
	@Test
	void isNotImperial() {
		UserInput testingImperial = new UserInput();
		assertFalse(testingImperial.validateImperial("1mperial"));
		assertFalse(testingImperial.validateImperial("inperial"));
	}

}

