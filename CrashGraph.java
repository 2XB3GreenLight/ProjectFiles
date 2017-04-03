package cas2xb3.greenlight;

import java.io.IOException;
import java.io.PrintWriter;

public class CrashGraph {

	private static Collision[] crashArr;

	// Calculates the severity of the inputed crash point
	public static double crashSeverity(double[] crashPoint) {

		double indSeverity = 1 / (2.0 * crashPoint[3]);
		double weather = 1 / (5.0 * crashPoint[4]);
		double paved = 0.9 + (crashPoint[5] / 10.0);

		double totalSeverity = indSeverity + weather + paved;
		return totalSeverity;
	}

	// The 4 crashes adjacent to the inputed crash
	public static int[] NSWECrashes(int crashIndex) {
		crashArr = Data.getArray();
		int[] NSWEcrash = new int[4];
		NSWEcrash[0] = 90000;
		NSWEcrash[1] = 90000;
		NSWEcrash[2] = 90000;
		NSWEcrash[3] = 90000;
		double distN = 100.0;
		double distS = 100.0;
		double distW = 100.0;
		double distE = 100.0;
		for (int i = 0; i < Data.getCollisionCount(); i++) {
			double otherLat = crashArr[i].getLat();
			double thisLat = crashArr[crashIndex].getLat();
			double otherLong = crashArr[i].getLng();
			double thisLong = crashArr[crashIndex].getLng();
			double posEdge = otherLong + (thisLat - thisLong);
			double negEdge = -otherLong + (thisLat + thisLong);
			// NORTHERN REGION
			if ((otherLat - thisLat) <= distN && otherLat > posEdge && otherLat > negEdge) {
				// if (otherLat > thisLat && (otherLat - thisLat) <= distN) {
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

		return NSWEcrash;
	}

	public static void main(String args[]) throws IOException {
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(Data.getCollisionCount());
		crashArr = Data.getArray();
		for (int i = 10000; i < 20000; i++) { // 10 instead of
										// Data.getCollisionCount()
			// if (NSWECrashes(i)[0] != 0 && NSWECrashes(i)[1] != 0 &&
			// NSWECrashes(i)[2] != 0 && NSWECrashes(i)[3] != 0) {
			int[] NSWEcrash = NSWECrashes(i);
			int n = NSWEcrash[0];
			int s = NSWEcrash[1];
			int w = NSWEcrash[2];
			int e = NSWEcrash[3];
			if (n != 90000) {
				DirectedEdge northC = new DirectedEdge(i, n, (crashArr[i]).getEquation() + (crashArr[n]).getEquation());
				G.addEdge(northC);
			}
			if (s != 90000) {
				DirectedEdge southC = new DirectedEdge(i, s, (crashArr[i]).getEquation() + (crashArr[s]).getEquation());
				G.addEdge(southC);
			}
			if (w != 90000) {
				DirectedEdge westC = new DirectedEdge(i, w, (crashArr[i]).getEquation() + (crashArr[w]).getEquation());
				G.addEdge(westC);
			}
			if (e != 90000) {
				DirectedEdge eastC = new DirectedEdge(i, e, (crashArr[i]).getEquation() + (crashArr[e]).getEquation());
				G.addEdge(eastC);
			}
			// }
		}
		PrintWriter wr = new PrintWriter("graph.txt");
		// System.out.println(Data.getCollisionCount());
		// System.out.println(NSWECrashes(1)[0]);
		// System.out.println(NSWECrashes(80672)[3]);
		// System.out.println(G.E());
		wr.println(G.toString());

		System.out.println(crashArr[12834].getLat());
		System.out.println(crashArr[57595].getLat());

		wr.close();

	}
}