package cas2xb3.greenlight;

import java.io.IOException;

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
		NSWEcrash[0] = 0;
		NSWEcrash[1] = 0;
		NSWEcrash[2] = 0;
		NSWEcrash[3] = 0;
		double distN = 100.0;
		double distS = 100.0;
		double distW = 100.0;
		double distE = 100.0;
		for (int i = 0; i < Data.getCollisionCount(); i++) {
			double otherLat = crashArr[i].getLat();
			double thisLat = crashArr[crashIndex].getLat();
			double otherLong = crashArr[i].getLng();
			double thisLong = crashArr[crashIndex].getLng();
			if (otherLat > thisLat && (otherLat - thisLat) <= distN) {
				distN = otherLat - thisLat;
				NSWEcrash[0] = i;
			}
			if (otherLat < thisLat && (thisLat - otherLat) <= distS) {
				distS = thisLat - otherLat;
				NSWEcrash[1] = i;
			}
			if (otherLong > thisLong && (otherLong - thisLong) <= distW) {
				distW = otherLong - thisLong;
				NSWEcrash[2] = i;
			}
			if (otherLong < thisLong && (thisLong - otherLong) <= distE) {
				distE = thisLong - otherLong;
				NSWEcrash[3] = i;
			}
		}

		return NSWEcrash;
	}


	/*public static void main(String args[]) throws IOException {

		EdgeWeightedDigraph G = new EdgeWeightedDigraph(Data.getCollisionCount());

		crashArr = Data.getArray();

		for (int i = 0; i < Data.getCollisionCount(); i++) {
			// if (northCrash(i) != 0 && southCrash(i) != 0 && westCrash(i) != 0
			// && eastCrash(i) != 0) {
			int[] NSWEcrash = NSWECrashes(i);
			int n = NSWEcrash[0];
			int s = NSWEcrash[1];
			int w = NSWEcrash[2];
			int e = NSWEcrash[3];
			DirectedEdge northC = new DirectedEdge(i, n, crashSeverity(crashArr[i]) + crashSeverity(crashArr[n]));
			G.addEdge(northC);
			DirectedEdge southC = new DirectedEdge(i, s, crashSeverity(crashArr[i]) + crashSeverity(crashArr[s]));
			G.addEdge(southC);
			DirectedEdge westC = new DirectedEdge(i, w, crashSeverity(crashArr[i]) + crashSeverity(crashArr[w]));
			G.addEdge(westC);
			DirectedEdge eastC = new DirectedEdge(i, e, crashSeverity(crashArr[i]) + crashSeverity(crashArr[e]));
			G.addEdge(eastC);
			// }
		}

		System.out.println(G.E());

		// System.out.println(Data.getCollisionCount());
		// System.out.println(northCrash(1));
		// System.out.println(southCrash(1));
		// System.out.println(westCrash(1));
		// System.out.println(eastCrash(80672));
		// System.out.println(crashArr[80672][2]);

	}
*/
}