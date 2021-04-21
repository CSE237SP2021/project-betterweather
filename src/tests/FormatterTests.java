package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import betterWeather.Formatter;

class FormatterTests {

	@Test
	void citiesWithNoSpaces() {
		Formatter chicago = new Formatter();
		String formattedChicago = chicago.formatCity("chicago");
		assertTrue(formattedChicago.equals("chicago"));
		
		Formatter phoenix = new Formatter();
		String formattedPhoenix= phoenix.formatCity("Phoenix");
		assertTrue(formattedPhoenix.equals("Phoenix"));
	}
	
	@Test
	void citiesWithSpaces() {
		Formatter nyc = new Formatter();
		String formattedNyc = nyc.formatCity("new york");
		assertTrue(formattedNyc.equals("new%20york"));
		
		Formatter rsm = new Formatter();
		String formattedRsm = rsm.formatCity("Rancho Santa Margarita");
		assertTrue(formattedRsm.equals("Rancho%20Santa%20Margarita"));
		
	}
	
	@Test
	void getFirstDigit() {
		assertEquals(Formatter.getFirstDigit(12345), 1);
		assertEquals(Formatter.getFirstDigit(23456), 2);
		assertEquals(Formatter.getFirstDigit(34567), 3);
		assertEquals(Formatter.getFirstDigit(45678), 4);
		assertEquals(Formatter.getFirstDigit(56789), 5);
		assertEquals(Formatter.getFirstDigit(67890), 6);
		assertEquals(Formatter.getFirstDigit(78901), 7);
		assertEquals(Formatter.getFirstDigit(89012), 8);
		assertEquals(Formatter.getFirstDigit(90123), 9);
	}
	
	@Test
	void capitalizeFirstLetterTest() {
		assertEquals(Formatter.capitalizeFirstLetter("hELLO"), "HELLO");
		assertEquals(Formatter.capitalizeFirstLetter("HELLO"), "HELLO");
		assertEquals(Formatter.capitalizeFirstLetter("Hello"), "Hello");
		assertEquals(Formatter.capitalizeFirstLetter("hello"), "Hello");
		assertEquals(Formatter.capitalizeFirstLetter(null), null);
	}

}
