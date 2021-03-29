package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import betterWeather.FormatCity;

class FormatCityTests {

	@Test
	void citiesWithNoSpaces() {
		FormatCity chicago = new FormatCity("chicago");
		String formattedChicago = chicago.formatCity();
		assertTrue(formattedChicago.equals("chicago"));
		
		FormatCity phoenix = new FormatCity("Phoenix");
		String formattedPhoenix= phoenix.formatCity();
		assertTrue(formattedPhoenix.equals("Phoenix"));
	}
	
	@Test
	void citiesWithSpaces() {
		FormatCity nyc = new FormatCity("new york");
		String formattedNyc = nyc.formatCity();
		assertTrue(formattedNyc.equals("new%20york"));
		
		FormatCity rsm = new FormatCity("Rancho Santa Margarita");
		String formattedRsm = rsm.formatCity();
		assertTrue(formattedRsm.equals("Rancho%20Santa%20Margarita"));
		
	}

}
