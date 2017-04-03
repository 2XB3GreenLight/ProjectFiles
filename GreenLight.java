package cas2xb3.greenlight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONException;

public class GreenLight {

	public static void main(String[] args) throws IOException {

		double originLat = 0;
		double originLng = 0;
		double destLat = 0;
		double destLng = 0;
		String origin = null;
		String destination = null;

		// Initialize and sort the collision array:

		Collision[] collisions = Data.getArray();
		Heap.sort(collisions);

		// Take the input from the user:

		try {
			Scanner sc = new Scanner(System.in);

			// Get the origin and turn it into lat/lng points:

			System.out.println("Enter the origin: ");
			origin = sc.nextLine();
			GeoCodingSample.getCoordinates(origin);
			originLat = GeoCodingSample.getLatitude();
			originLng = GeoCodingSample.getlongitude();

			// Get the destination and turn it into lat/lng points:

			System.out.println("Enter the destination: ");
			destination = sc.nextLine();
			GeoCodingSample.getCoordinates(destination);
			destLat = GeoCodingSample.getLatitude();
			destLng = GeoCodingSample.getlongitude();

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Find the collisions closest to the origin and destination:

		int originPointIndex = ClosestPoint.closest(collisions, originLat, originLng);
		int destPointIndex = ClosestPoint.closest(collisions, destLat, destLng);

		System.out.println(originPointIndex);
		System.out.println(destPointIndex);
		
		// Build the graph

		EdgeWeightedDigraph G = CrashGraph.FinalGraph();

		// Then find the shortest path from the origin to the destination:

		DijkstraSP sp = new DijkstraSP(G, originPointIndex);

		// Make the array of the collisions:
		
		ArrayList<Collision> tempCollisionPath = new ArrayList<Collision>();
		tempCollisionPath.add(collisions[originPointIndex]);
		
		if (sp.hasPathTo(destPointIndex)) {
			System.out.printf("%s to %s (%.2f)  ", origin, destination, sp.distTo(destPointIndex));
			for (DirectedEdge e : sp.pathTo(destPointIndex)) {
				tempCollisionPath.add(collisions[e.to()]);
			}
		}
		
		Object[] collisionPath = tempCollisionPath.toArray();

		
		for (int i = 0; i < collisionPath.length; i++){
			System.out.println(((Collision) collisionPath[i]).getLat() + " , " + ((Collision) collisionPath[i]).getLng());
		}
	}

}