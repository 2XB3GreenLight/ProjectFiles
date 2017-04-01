package cas2xb3.greenlight;

import java.util.Scanner;

public class GreenLight {

	public static void main(String[] args) {

		// Take the input from the user:
		/*
		 * Scanner keyboard = new Scanner(System.in);
		 * System.out.println("Insert x-coordinate of destination: "); double
		 * xcrd = keyboard.nextDouble();
		 * System.out.println("Insert y-coordinate of destination: "); double
		 * ycrd = keyboard.nextDouble();
		 */

		EdgeWeightedDigraph G = new EdgeWeightedDigraph(Data.getCollisionCount());

		Collision[] crashArr = Data.getArray();

		// First, make the graph:
		for (int i = 0; i < 100; i++) {
			// if (northCrash(i) != 0 && southCrash(i) != 0 && westCrash(i) != 0
			// && eastCrash(i) != 0) {
			int[] NSWEcrash = CrashGraph.NSWECrashes(i);
			int n = NSWEcrash[0];
			int s = NSWEcrash[1];
			int w = NSWEcrash[2];
			int e = NSWEcrash[3];
			DirectedEdge northC = new DirectedEdge(i, n, (crashArr[i].getEquation()) + (crashArr[n].getEquation()));
			G.addEdge(northC);
			DirectedEdge southC = new DirectedEdge(i, s, (crashArr[i].getEquation()) + (crashArr[s].getEquation()));
			G.addEdge(southC);
			DirectedEdge westC = new DirectedEdge(i, w, (crashArr[i].getEquation()) + (crashArr[w].getEquation()));
			G.addEdge(westC);
			DirectedEdge eastC = new DirectedEdge(i, e, (crashArr[i].getEquation()) + (crashArr[e].getEquation()));
			G.addEdge(eastC);
			// }
		}

		// Then find the shortest paths from point 1 (for example) to whatever:
		DijkstraSP sp = new DijkstraSP(G, 1);

		// print shortest path from point 1 to t:
		for (int t = 0; t < G.V(); t++) {
			if (sp.hasPathTo(t)) {
				System.out.printf("%d to %d (%.2f)  ", 1, t, sp.distTo(t));
				for (DirectedEdge e : sp.pathTo(t)) {
					System.out.print(e + "   ");
				}
				System.out.println();
			}
		}

//		Collision[] collisions = newData.getArray();


//		HeapSort.sort(collisions);
//		for (int i = 0; i < collisions.length; i++) {
//			System.out.print(collisions[i].getIndex() + " ");
//			System.out.print(collisions[i].getLat() + " ");
//			System.out.print(collisions[i].getLng() + " ");
//			System.out.print(collisions[i].getSeverity() + " ");
//			System.out.print(collisions[i].getWeather() + " ");
//			System.out.print(collisions[i].getPaved());
//			System.out.println();
//		}

		// System.out.println(g.toString());
		// System.out.println(Data.equation(80681));
	}

}