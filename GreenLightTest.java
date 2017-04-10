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
import cas2xb3.greenlight.GreenLight;
import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Heap;

public class GreenLightTest {
	
	private static List<String> path;

	private static String origin;

	private static String destination;

	@Before
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
			
			origin = "Drake University";
			
			destination = "Des Moines Airport";

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
			
			origin = "Siox City";
			
			destination = "Iowa City";

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
}