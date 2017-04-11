package cas2xb3.greenlightTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cas2xb3.greenlight.Collision;

public class CollisionTest {

	private static Collision Collision1;
	private static Collision Collision2;
	private static Collision Collision3;

	@Before
	public void setUp() throws Exception {
		Collision1 = new Collision(16, 42.015281695000056, -94.37609281699997, 5, 1, 1);
		Collision2 = new Collision(15, 41.59552804200007, -93.59872212399995, 5, 2, 1);
		Collision3 = new Collision(17, 43.14836916100006, -93.22102043599995, 4, 1, 1);
	}

	@After
	public void tearDown() throws Exception {
		Collision1 = null;
		Collision2 = null;
		Collision3 = null;
	}

	@Test
	public void getIndexTest() {
		int indexCheck = Collision1.getIndex();
		assertTrue(indexCheck == 16);
	}

	@Test
	public void getLatTest() {
		double latCheck = Collision1.getLat();
		assertTrue(latCheck == 42.015281695000056);
	}

	@Test
	public void getLngTest() {
		double lngCheck = Collision1.getLng();
		assertTrue(lngCheck == -94.37609281699997);
	}

	@Test
	public void getSeverityTest() {
		int severityCheck = Collision1.getSeverity();
		assertTrue(severityCheck == 5);
	}

	@Test
	public void getWeatherTest() {
		int weatherCheck = Collision1.getWeather();
		assertTrue(weatherCheck == 1);
	}

	@Test
	public void getPavedTest() {
		int pavedCheck = Collision1.getPaved();
		assertTrue(pavedCheck == 1);
	}

	@Test
	public void getEquationTest() {
		double equationCheck = Collision1.getEquation();
		double equationCalc = (1.0 / (2 * 5) + 1 / (5 * 1)) * (0.9 + (1 / 10));
		assertTrue(equationCheck == equationCalc);
	}
	
	@Test
	// Tests the compareTo method with a collision > the collision being compared to 
	public void compareToTest() {
		int compare = Collision1.compareTo(Collision2);
		assertTrue(compare == 1);
	}
	
	@Test
	// Tests the compareTo method with a collision < the collision being compared to 
	public void compareToTest2() {
		int compare = Collision1.compareTo(Collision3);
		assertTrue(compare == -1);
	}
	
	@Test
	// Tests the compareTo method with a collision = the collision being compared to 
	public void compareToTest3() {
		int compare = Collision1.compareTo(Collision1);
		assertTrue(compare == 0);
	}

}