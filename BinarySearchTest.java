package cas2xb3.greenlightTests;

import cas2xb3.greenlight.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.BinarySearch;

public class BinarySearchTest {
	public Comparable[] x = {1,3,5,6,7,8,9,11,23,44,46,56,77,102,302,404,505,600};
	@Before//         0 1 2 3 4 5 6 7  8  9  10 11 12 13 14 15 
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		x=null;
	}

	@Test
	public void test() {
		boolean result = true;
		if(cas2xb3.greenlight.BinarySearch.indexOf(x, -100, 0, x.length) != 0){
			result = false;
		}
		if(cas2xb3.greenlight.BinarySearch.indexOf(x, 4, 0, x.length) != 2){
			result = false;
		}
		if(cas2xb3.greenlight.BinarySearch.indexOf(x, 15, 0, x.length) != 8){
			result = false;
		}
		if(cas2xb3.greenlight.BinarySearch.indexOf(x, 45, 0, x.length) != 10){
			result = false;
		}
		if(cas2xb3.greenlight.BinarySearch.indexOf(x, 200, 0, x.length) != 14){
			result = false;
		}
		if(cas2xb3.greenlight.BinarySearch.indexOf(x, 900, 0, x.length) != 18){
			result = false;
		}
		assertTrue(result);
	}

}
