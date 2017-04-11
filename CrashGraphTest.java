package cas2xb3.greenlightTests;

import static org.junit.Assert.*;
import edu.princeton.cs.algs4.*;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cas2xb3.greenlight.CrashGraph;
import cas2xb3.greenlight.Data;

public class CrashGraphTest {

	private static EdgeWeightedDigraph G;
	private static int numOfCrashes;

	@Before
	public void setUp() throws Exception {
		G = CrashGraph.FinalGraph();
		numOfCrashes = Data.getCollisionCount();

	}

	@After
	public void tearDown() throws Exception {
		G = null;
		numOfCrashes = 0;
	}

	@Test
	// Tests the crash point at index 0; and the adjacent north, south, west, and east crashes
	public void NSWECrashesTest1() {
		boolean result = false;
		int[] NSWEfor0 = CrashGraph.NSWECrashes(0);
		// Compares the results with the results found from the data set
		if (NSWEfor0[0] == 1832 && NSWEfor0[1] == 303 && NSWEfor0[2] == 234 && NSWEfor0[3] == 3244 ){
			result = true;
		}
		assertTrue(result);
	}

	@Test
	// Tests point that has no eastern crash
	public void NSWECrashesTest2() {
		boolean result = false;
		int[] NSWE = CrashGraph.NSWECrashes(1021);
		// Compares the results with the results found from the data set
		// 90000 is the null point
		if (NSWE[0] == 2907 && NSWE[1] == 490 && NSWE[2] == 253 && NSWE[3] == 90000 ){
			result = true;
		}
		assertTrue(result);
	}

	@Test
	// Confirms that the graph is not empty
	public void FinalGraphTest() throws IOException {
		assertTrue(G != null);
	}

	@Test
	// Confirms that the graph is the right size
	public void FinalGraphTest2() throws IOException {
		assertTrue(G.V() == numOfCrashes);
	}

}
