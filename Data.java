package cas2xb3.greenlight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;

public class Data {

	static private int size = getCollisionCount();
	static private int[] collisionArray = new int[size];
	static private Collision[] actualCollisionArray = new Collision[size];

	public Data() {
	}

	/**
	 * @return an array with only the important information. Also gives each row
	 *         an index to be referenced by
	 */
	public static Collision[] getArray() {

		// If the array has already been filled:
		if (actualCollisionArray[0] != null) {
			return actualCollisionArray;
		}

		try {
			BufferedReader br = new BufferedReader(new FileReader("data/Crash_Data.csv"));
			String line;

			// Put all the lines in the array (important information) with the
			// crash index:
			br.readLine();
			
			for (int i = 0; i < getCollisionCount(); i++) {
				line = br.readLine();
				if (line.charAt(0) == '2') { // Only consider the rows with
												// crash information
					String splitline[] = line.split(",");

					collisionArray[i] = i;

					double lat = Double.valueOf(splitline[39].substring(2)); // LATITUDE
					double lng = -1 * Double.valueOf(splitline[40].substring(2, splitline[40].indexOf(')'))); // LONGITUDE
					int CSEV;
					if (!splitline[25].equals("")) {
						CSEV = Integer.valueOf(splitline[25]); // CSEV
					} else {
						CSEV = 5; // assume non-severe collision
					}
					int weather;
					if (Integer.valueOf(splitline[20]) <= 10) {
						weather = Integer.valueOf(splitline[20]); // WEATHER
					} else {
						weather = 5; // average weather
					}
					int paved;
					if (Integer.valueOf(splitline[23]) <= 2) {
						paved = Integer.valueOf(splitline[23]); // PAVED
					} else {
						paved = 1; // assume paved
					}

					actualCollisionArray[i] = new Collision(i, lat, lng, CSEV, weather, paved);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return actualCollisionArray;
	}

	/**
	 * @return the total number of collisions
	 */
	public static int getCollisionCount() {

		// If the size has already been calculated:
		if (size != 0) {
			return size;
		}

		// The first time it's calculated:
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/Crash_Data.csv"));

			br.readLine(); // skip the first line in the dataset
			// Iterate through file line by line:
			while (br.readLine().charAt(0) == '2') {
				size++;
			}

			br.close();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		size--;
		return size;
	}

	public static void printCollisionData() {
		for (int i = 0; i < size; i++) {
			System.out.print(actualCollisionArray[i].getIndex() + " ");
			System.out.print(actualCollisionArray[i].getLat() + " ");
			System.out.print(actualCollisionArray[i].getLng() + " ");
			System.out.print(actualCollisionArray[i].getSeverity() + " ");
			System.out.print(actualCollisionArray[i].getWeather() + " ");
			System.out.print(actualCollisionArray[i].getPaved());
			System.out.println();
		}
	}

}
