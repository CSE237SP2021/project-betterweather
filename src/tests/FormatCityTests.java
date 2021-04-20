package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import betterWeather.Formatter;

class FormatCityTests {

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

}
