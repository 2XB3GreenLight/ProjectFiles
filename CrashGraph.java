package cas2xb3.greenlight;

import java.io.IOException;
import java.io.PrintWriter;

import edu.princeton.cs.algs4.*;

/**
 * A class which represents a graph connecting all of the collisions to
 * replicate the roads of Iowa.
 * 
 * @author Abrar Attia
 * @author Mediha Munim
 */
public class CrashGraph {

	private static Collision[] crashArr;

	/**
	 * Calculates the indices of the four collisions that are adjacent to the
	 * given collision (to the North, South, East and West).
	 * 
	 * @param crashIndex
	 *            The particular collision index
	 * @return
	 */
	public static int[] NSWECrashes(int crashIndex) {
		crashArr = Data.getArray();
		int[] NSWEcrash = new int[4];
		// Data point (90000) outside of reach used as null point
		NSWEcrash[0] = 90000;
		NSWEcrash[1] = 90000;
		NSWEcrash[2] = 90000;
		NSWEcrash[3] = 90000;
		// Maximum distance between two adjacent nodes
		double distN = 100.0;
		double distS = 100.0;
		double distW = 100.0;
		double distE = 100.0;

		// Reduces the size of the crash data
		int low = crashIndex - 5000;
		int hi = crashIndex + 5000;
		if (low < 0)
			low = 0;
		if (hi > Data.getCollisionCount())
			hi = Data.getCollisionCount();

		// Compares all the crash nodes within the low and high range
		for (int i = low; i < hi; i++) {
			// Stores the latitude and longitude of crash in reference and one
			// being compared
			double otherLat = crashArr[i].getLat();
			double thisLat = crashArr[crashIndex].getLat();
			double otherLong = crashArr[i].getLng();
			double thisLong = crashArr[crashIndex].getLng();
			// Stores the spotlight range to find crashes
			double posEdge = otherLong + (thisLat - thisLong);
			double negEdge = -otherLong + (thisLat + thisLong);

			// NORTHERN REGION
			if ((otherLat - thisLat) <= distN && otherLat > posEdge && otherLat > negEdge) {
				distN = otherLat - thisLat;
				NSWEcrash[0] = i;
			}
			// SOUTHERN REGION
			if ((thisLat - otherLat) <= distS && otherLat < negEdge && otherLat < posEdge) {
				distS = thisLat - otherLat;
				NSWEcrash[1] = i;
			}
			// WESTERN REGION
			if ((thisLong - otherLong) <= distW && (thisLong - otherLong) > 0 && otherLat < negEdge
					&& otherLat > posEdge) {
				distW = thisLong - otherLong;
				NSWEcrash[2] = i;
			}
			// EASTERN REGION
			if ((otherLong - thisLong) <= distE && (otherLong - thisLong) > 0 && otherLat < posEdge
					&& otherLat > negEdge) {
				distE = otherLong - thisLong;
				NSWEcrash[3] = i;
			}
		}
		// Returns the closest northern, southern, western, and eastern crashes
		// to the crash in reference
		return NSWEcrash;
	}

	/**
	 * Creates a graph from all of the collisions by connecting each collision
	 * to the points North, South, East and West of it.
	 * 
	 * @return The graph of the collisions.
	 * @throws IOException
	 */
	public static EdgeWeightedDigraph FinalGraph() throws IOException {
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(Data.getCollisionCount());
		crashArr = Data.getArray();
		for (int i = 0; i < G.V(); i++) {
			// Determines the 4 crashes each crash will be connected to
			int[] NSWEcrash = NSWECrashes(i);
			int n = NSWEcrash[0];
			int s = NSWEcrash[1];
			int w = NSWEcrash[2];
			int e = NSWEcrash[3];
			// Confirms that the crash is not connected to a null point
			if (n != 90000) {
				// Creates edge from the crash in reference to the closest
				// northern crash
				DirectedEdge northC = new DirectedEdge(i, n, (crashArr[i]).getEquation() + (crashArr[n]).getEquation());
				G.addEdge(northC);
			}
			// Confirms that the crash is not connected to a null point
			if (s != 90000) {
				// Creates edge from the crash in reference to the closest
				// southern crash
				DirectedEdge southC = new DirectedEdge(i, s, (crashArr[i]).getEquation() + (crashArr[s]).getEquation());
				G.addEdge(southC);
			}
			// Confirms that the crash is not connected to a null point
			if (w != 90000) {
				// Creates edge from the crash in reference to the closest
				// western crash
				DirectedEdge westC = new DirectedEdge(i, w, (crashArr[i]).getEquation() + (crashArr[w]).getEquation());
				G.addEdge(westC);
			}
			// Confirms that the crash is not connected to a null point
			if (e != 90000) {
				// Creates edge from the crash in reference to the closest
				// eastern crash
				DirectedEdge eastC = new DirectedEdge(i, e, (crashArr[i]).getEquation() + (crashArr[e]).getEquation());
				G.addEdge(eastC);
			}
		}
		// Returns the complete graph
		return G;
	}

}