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
	
	@Test
	void roundToTwoDecimalsTest() {
		assertEquals(Formatter.roundToTwoDecimals(3.111), 3.11);
		assertEquals(Formatter.roundToTwoDecimals(3), 3.00);
		assertEquals(Formatter.roundToTwoDecimals(0.0001), 0.00);
		assertEquals(Formatter.roundToTwoDecimals(1.179), 1.18);
	}
	
	
	@Test
	void getUnitTrue() {
		assertEquals(Formatter.getUnit("imperial"), "F");
		assertEquals(Formatter.getUnit("IMPERIAL"), "F");
		assertEquals(Formatter.getUnit("IMPErial"), "F");
		assertEquals(Formatter.getUnit("metric"), "C");
		assertEquals(Formatter.getUnit("METRIC"), "C");
		assertEquals(Formatter.getUnit("METRic"), "C");
	}

	@Test
	void getUnitFalse() {
		assertNotEquals(Formatter.getUnit(" "), "F");
		assertNotEquals(Formatter.getUnit("1MPERIAL"), "F");
		assertNotEquals(Formatter.getUnit("metric"), "F");
		assertNotEquals(Formatter.getUnit("metric"), "F");
		assertNotEquals(Formatter.getUnit("M3tric"), "C");
		assertNotEquals(Formatter.getUnit("kelvin"), "C");
	}
	
	@Test
	void getOutfitImperialTrue() {
		assertEquals(Formatter.getOutfitImperial(0.0), "Consider double layer and thermos");
		assertEquals(Formatter.getOutfitImperial(31.2), "Winter coat, gloves and beanie");
		assertEquals(Formatter.getOutfitImperial(45.6), "Jacket");
		assertEquals(Formatter.getOutfitImperial(57.54), "Warm hoodie or sweater");
		assertEquals(Formatter.getOutfitImperial(80.9), "Regular summer wear");
		assertEquals(Formatter.getOutfitImperial(100.0), "Too hot! sunscreen use is recommended");
	}
	
	@Test
	void getOutfitImperialFalse() {
		assertNotEquals(Formatter.getOutfitImperial(-1.2), "Not available");
		assertNotEquals(Formatter.getOutfitImperial(2.3), "Winter coat, gloves and beanie");
		assertNotEquals(Formatter.getOutfitImperial(50.7), "hoodie");
		assertNotEquals(Formatter.getOutfitImperial(40.5), "flip flops");
		assertNotEquals(Formatter.getOutfitImperial(200), "glasses");
		assertNotEquals(Formatter.getOutfitImperial(100.0), "Sunscreen");
	}
	
	@Test
	void getOutfitMetricTrue() {
		assertEquals(Formatter.getOutfitMetric(-2), "Consider double layer and thermos");
		assertEquals(Formatter.getOutfitMetric(3), "Winter coat, gloves and beanie");
		assertEquals(Formatter.getOutfitMetric(5), "Jacket");
		assertEquals(Formatter.getOutfitMetric(18.5), "Warm hoodie or sweater");
		assertEquals(Formatter.getOutfitMetric(28.5), "Regular summer wear");
		assertEquals(Formatter.getOutfitMetric(35.7), "Too hot! sunscreen use is recommended");
	}
	
	@Test
	void getOutfitMetricFalse() {
		assertNotEquals(Formatter.getOutfitMetric(-1.2), "Not available");
		assertNotEquals(Formatter.getOutfitMetric(-5), "Winter coat, gloves and beanie");
		assertNotEquals(Formatter.getOutfitMetric(4), "hoodie");
		assertNotEquals(Formatter.getOutfitMetric(36), "flip flops");
		assertNotEquals(Formatter.getOutfitMetric(8), "glasses");
		assertNotEquals(Formatter.getOutfitMetric(11), "Sunscreen");
	}
}

