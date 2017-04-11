package cas2xb3.greenlightTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cas2xb3.greenlight.Collision;
import cas2xb3.greenlight.Data;

public class DataTest {

	private static Collision[] crashArr;
	
	private static int numOfCollisions;
	
	@Before
	public void setUp() throws Exception {
		crashArr = Data.getArray();
		numOfCollisions = Data.getCollisionCount();
	}

	@After
	public void tearDown() throws Exception {
		crashArr = null;
		numOfCollisions = 0;
	}

	@Test
	// Confirms that the crash array is not empty
	public void getArrayTest() {
		assertTrue(crashArr != null);
	}
	
	@Test
	// Confirms that the first crash in the array matches the Crash_Data dataset.
	public void getArrayTest2() {
		Collision firstCrash = crashArr[0];
		assertTrue(firstCrash.getLat()== 41.56093317800003 && firstCrash.getLng() == -93.60691740599998);
	}
	
	@Test
	// Confirms that the last crash in the array matches the Crash_Data dataset.
	public void getArrayTest3() {
		Collision lastCrash = crashArr[numOfCollisions-1];
		assertTrue(lastCrash.getLat()== 42.034764093000035 && lastCrash.getLng() ==  -91.67532881299996);
	}

	
	@Test
	// Confirms that the number of crashes determined is correct
	public void getCollisionCountTest() {
		assertTrue(numOfCollisions == 80673);
	}

}