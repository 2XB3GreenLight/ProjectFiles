package cas2xb3.greenlight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Data {

	static private int size = getCollisionCount();
	static private double[][] collisionArray = new double[size][6];
	
	public Data() {
	}

	/**
	 * 
	 * @return an array with only the important information. Also gives each row
	 *         an index to be referenced by
	 */
	public static double[][] getArray() {

		// If the array has already been filled:

		if (collisionArray[0][0] != 0) {
			return collisionArray;
		}

		try {
			BufferedReader br = new BufferedReader(new FileReader("data/Crash_Data.csv"));
			String line;

			// Put all the lines in the array (important information) with the
			// crash index:
			for (int i = 0; i < getCollisionCount(); i++) {
				line = br.readLine();
				if (line.charAt(0) == '2') { // Only consider the rows with
												// crash information
					String splitline[] = line.split(",");

					collisionArray[i][0] = i;
					collisionArray[i][1] = Double.valueOf(splitline[39].substring(2)); // LATITUDE
					collisionArray[i][2] = -1*Double.valueOf(splitline[40].substring(2, splitline[40].indexOf(')'))); // LONGITUDE
					if (!splitline[25].equals("")) {
						collisionArray[i][3] = Integer.valueOf(splitline[25]); // CSEV
					} else {
						collisionArray[i][3] = 5; // assume non-severe collision
					}
					if (Integer.valueOf(splitline[20]) <= 10) {
						collisionArray[i][4] = Integer.valueOf(splitline[20]); // WEATHER
					} else {
						collisionArray[i][4] = 5; // average weather
					}
					if (Integer.valueOf(splitline[23]) <= 2) {
						collisionArray[i][5] = Integer.valueOf(splitline[23]); // PAVED
					} else {
						collisionArray[i][5] = 1; // assume paved
					}
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return collisionArray;
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
	
	public static double getLat(int crashNumb) {
		return collisionArray[crashNumb][1];
	}

	public static double getLng(int crashNumb) {
		return collisionArray[crashNumb][2];
	}

	public static double getCSEV(int crashNumb) {
		return collisionArray[crashNumb][3];
	}

	public static double getWeather(int crashNumb) {
		return collisionArray[crashNumb][4];
	}

	public static double getPaved(int crashNumb) {
		return collisionArray[crashNumb][5];
	}

	public static void printArray() {
		for (int i = 0; i < collisionArray.length; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(collisionArray[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * crashNumb is the index of the crash in collisionArray
	 * @return 
	 */
	public static double equation(int crashNumb) {
		return (1.0/(2*getCSEV(crashNumb))+ 1/(5*getWeather(crashNumb)))*(0.9+(getPaved(crashNumb)/10));
	}

}
