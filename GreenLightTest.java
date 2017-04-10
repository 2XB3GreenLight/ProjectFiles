package cas2xb3.greenlightTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import cas2xb3.greenlight.ClosestPoint;
import cas2xb3.greenlight.Collision;
import cas2xb3.greenlight.CrashGraph;
import cas2xb3.greenlight.Data;
import cas2xb3.greenlight.GoogleMapsGeocoder;
import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Heap;

/**
 * This method tests our GreenLight.java class using two different test cases.
 * @author Gundeep
 *
 */
public class GreenLightTest {
	
	// Variables to be used throughout the class
	
	private static List<String> path;

	private static String origin;

	private static String destination;

	@Before
	/**
	 * The following method is used to set up the first test case.
	 * The code in the method is directly from the GreenLight.java class, except for the inputs which are determined to be from
	 * Drake University to Des Moines Airport.
	 * 
	 * This test case was chosen to represent a typical scenario, the two locations are located in the same city and it is a route someone might actually want to take.
	 * @throws Exception
	 */
	public void setUp1() throws Exception {
		
		double originLat = 0;
		double originLng = 0;
		double destLat = 0;
		double destLng = 0;

		// Initialize and sort the collision array:

		Collision[] collisions = Data.getArray();
		Heap.sort(collisions);

		// Take the input from the user:

		try {
			Scanner sc = new Scanner(System.in);

			// Get the origin and turn it into lat/lng points:
			
			origin = "Drake University, Iowa";
			
			destination = "Des Moines Airport, Iowa";

			origin = GoogleMapsGeocoder.getCoordinates(origin);

			originLat = GoogleMapsGeocoder.getLatitude();
			originLng = GoogleMapsGeocoder.getLongitude();

			if (originLat > 43.51 || originLat < 40.22 || originLng > -90.15 || originLng < -96.65) {
				IOException e = new IOException();
				System.out.println("Origin is not in Iowa.");
				throw e;
			}

			// Get the destination and turn it into lat/lng points:

			destination = GoogleMapsGeocoder.getCoordinates(destination);
			destLat = GoogleMapsGeocoder.getLatitude();
			destLng = GoogleMapsGeocoder.getLongitude();

			if (destLat > 43.51 || destLat < 40.22 || destLng > -90.15 || destLng < -96.65) {
				IOException e = new IOException();
				System.out.println("Origin is not in Iowa.");
				throw e;
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Find the collisions closest to the origin and destination:

		int originPointIndex = ClosestPoint.closest(collisions, originLat, originLng);
		int destPointIndex = ClosestPoint.closest(collisions, destLat, destLng);

		// Build the graph

		EdgeWeightedDigraph G = CrashGraph.FinalGraph();

		// Then find the shortest path from the origin to the destination:

		DijkstraSP sp = new DijkstraSP(G, originPointIndex);

		// Make the array of the collisions:

		ArrayList<Collision> tempCollisionPath = new ArrayList<Collision>();
		tempCollisionPath.add(collisions[originPointIndex]);

		if (sp.hasPathTo(destPointIndex)) {
			// System.out.printf("%s to %s (%.2f) ", origin, destination,
			// sp.distTo(destPointIndex));
			for (DirectedEdge e : sp.pathTo(destPointIndex)) {
				tempCollisionPath.add(collisions[e.to()]);
			}
		}

		path = new ArrayList<String>();
		
		Object[] collisionPath = tempCollisionPath.toArray();

		for (int i = 0; i < collisionPath.length; i++) {
			path.add(((Collision) collisionPath[i]).getLat() + "," + ((Collision) collisionPath[i]).getLng());
		}
		
	}
	
	@Before
	/**
	 * The following method is used to set up the second test case.
	 * The code in the method is directly from the GreenLight.java class, except for the inputs which are determined to be from
	 * Sioux City to Davenport.
	 * 
	 * This test case was chosen to represent an edge case, as the two locations are not only located very far from eachother, but are located 
	 * on the borders of the state on the western and eastern edges of Iowa. This is done to show that our program will still work even if the input
	 * locations are close to being out of the state.
	 * @throws Exception
	 */
	public void setUp2() throws Exception {
		
		double originLat = 0;
		double originLng = 0;
		double destLat = 0;
		double destLng = 0;

		// Initialize and sort the collision array:

		Collision[] collisions = Data.getArray();
		Heap.sort(collisions);

		// Take the input from the user:

		try {
			Scanner sc = new Scanner(System.in);

			// Get the origin and turn it into lat/lng points:
			
			origin = "Sioux City, Iowa";
			
			destination = "Davenport, Iowa";

			origin = GoogleMapsGeocoder.getCoordinates(origin);

			originLat = GoogleMapsGeocoder.getLatitude();
			originLng = GoogleMapsGeocoder.getLongitude();

			if (originLat > 43.51 || originLat < 40.22 || originLng > -90.15 || originLng < -96.65) {
				IOException e = new IOException();
				System.out.println("Origin is not in Iowa.");
				throw e;
			}

			// Get the destination and turn it into lat/lng points:

			destination = GoogleMapsGeocoder.getCoordinates(destination);
			destLat = GoogleMapsGeocoder.getLatitude();
			destLng = GoogleMapsGeocoder.getLongitude();

			if (destLat > 43.51 || destLat < 40.22 || destLng > -90.15 || destLng < -96.65) {
				IOException e = new IOException();
				System.out.println("Origin is not in Iowa.");
				throw e;
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Find the collisions closest to the origin and destination:

		int originPointIndex = ClosestPoint.closest(collisions, originLat, originLng);
		int destPointIndex = ClosestPoint.closest(collisions, destLat, destLng);

		// Build the graph

		EdgeWeightedDigraph G = CrashGraph.FinalGraph();

		// Then find the shortest path from the origin to the destination:

		DijkstraSP sp = new DijkstraSP(G, originPointIndex);

		// Make the array of the collisions:

		ArrayList<Collision> tempCollisionPath = new ArrayList<Collision>();
		tempCollisionPath.add(collisions[originPointIndex]);

		if (sp.hasPathTo(destPointIndex)) {
			// System.out.printf("%s to %s (%.2f) ", origin, destination,
			// sp.distTo(destPointIndex));
			for (DirectedEdge e : sp.pathTo(destPointIndex)) {
				tempCollisionPath.add(collisions[e.to()]);
			}
		}

		path = new ArrayList<String>();
		
		Object[] collisionPath = tempCollisionPath.toArray();

		for (int i = 0; i < collisionPath.length; i++) {
			path.add(((Collision) collisionPath[i]).getLat() + "," + ((Collision) collisionPath[i]).getLng());
		}
		
	}
	
	@Test
	/**
	 * This method tests the first test case by iterating through the generated path, and then iterating through the array of all collision locations in the dataset.
	 * It checks if every location in path is a real collision in our dataset.
	 * @throws Exception
	 */
	public void test1() throws Exception {
		
		setUp1();
		
		int exist = 0;
				
		Collision[] dataset = Data.getArray();
		
		for(int i = 0; i < path.size() ; i++){
			
			for(int j = 0; j < dataset.length ; j++){
				
				if( path.get(i).equals(dataset[j].getLat() + "," + dataset[j].getLng())){
					
					exist = 1;
					
				}
			
			}
			
			equals(exist == 1);
			
			exist = 0;
		}
	}
	
	@Test
	/**
	 * This method tests the first test case by iterating through the generated path, and then iterating through the array of all collision locations in the dataset.
	 * It checks if every location in path is a real collision in our dataset.
	 * @throws Exception
	 */
	public void test2() throws Exception {
		
		setUp2();
		
		int exist = 0;
				
		Collision[] dataset = Data.getArray();
		
		for(int i = 0; i < path.size() ; i++){
			
			for(int j = 0; j < dataset.length ; j++){
				
				if( path.get(i).equals(dataset[j].getLat() + "," + dataset[j].getLng())){
					
					exist = 1;
					
				}
			
			}
			
			equals(exist == 1);
			
			exist = 0;
		}
	}
