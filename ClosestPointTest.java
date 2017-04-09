package cas2xb3.greenlightTests;

import cas2xb3.greenlight.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClosestPointTest {
	public Collision[] x;
	@Before
	public void setUp() throws Exception {
		System.out.println("Testing ClosestPoint Class");
		x = new Collision[20];
		x[0] = new Collision(0, 40.2, 90.8, 0, 0, 0);
		x[1] = new Collision(1, 40.35, 91.1, 0, 0, 0);
		x[2] = new Collision(2, 40.5, 91.4, 0, 0, 0);
		x[3] = new Collision(3, 40.65, 91.7, 0, 0, 0);
		x[4] = new Collision(4, 40.8, 92.0, 0, 0, 0);
		x[5] = new Collision(5, 40.95, 92.3, 0, 0, 0);
		x[6] = new Collision(6, 41.1, 92.6, 0, 0, 0);
		x[7] = new Collision(7, 41.25, 92.9, 0, 0, 0);
		x[8] = new Collision(8, 41.4, 93.2, 0, 0, 0);
		x[9] = new Collision(9, 41.55, 93.5, 0, 0, 0);
		x[10] = new Collision(10, 41.7, 93.8, 0, 0, 0);
		x[11] = new Collision(11, 41.85, 94.1, 0, 0, 0);
		x[12] = new Collision(12, 42.0, 94.4, 0, 0, 0);
		x[13] = new Collision(13, 42.15, 94.7, 0, 0, 0);
		x[14] = new Collision(14, 42.3, 95.0, 0, 0, 0);
		x[15] = new Collision(15, 42.45, 95.3, 0, 0, 0);
		x[16] = new Collision(16, 42.6, 95.6, 0, 0, 0);
		x[17] = new Collision(17, 42.75, 95.9, 0, 0, 0);
		x[18] = new Collision(18, 42.9, 96.1, 0, 0, 0);
		x[19] = new Collision(19, 43.05, 96.4, 0, 0, 0);
		
		
	}

	@After
	public void tearDown() throws Exception {
		x=null;
		System.out.println("Testing Procedures finished");
	}

	@Test
	public void testClosest() {
		boolean result=true;
		for(int i = 0 ;i<20;i++){
			if(ClosestPoint.closest(x, x[i].getLat(), x[i].getLng()) != i){
				result = false;

			}
		}
		if(ClosestPoint.closest(x, 41.866, 0.0) != 11){
			result = false;
		}
		assertTrue(result);	
		}

}
