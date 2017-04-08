package cas2xb3.greenlight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * This method is used to help generate the safest route that our algorithms determined.
 * It contains methods which modifies the maps.txt file in order for it to generate a map output which meets our specifications.
 * It also contains a method which resets the file, enabling our program to be reused.
 * 
 * Note: The html/javascript code found in maps.txt and mapsref.txt were created with the help of the Google Maps Javascript API.
 * Links: https://developers.google.com/maps/documentation/javascript/examples/directions-waypoints , https://developers.google.com/maps/documentation/javascript/examples/directions-panel
 */
public class GoogleMapsDirectionsGenerator {
	private static String location;
	private static String destination;
	private static String oldFileName;
	private static String tmpFileName;
	private static BufferedWriter bw;
	private static BufferedReader br;
	private static String line;
	private static List<String> list;
	private static List<String> newlist;

	/**
	 * This class modifies a list of strings which contains all the waypoints that our route travels through (all the nodes of collisions that the path goes through),
	 * ensuring that the route created by google maps will function.
	 * Google Maps can only generate routes with up to 23 waypoints, this program ensures that if there are more than that many nodes in the generated route the program will still run.
	 * It also ensures that if there are more than 23 waypoints, the route generated will contain nodes from locations throughout the route, in order to maintain a level of correctness.
	 * 
	 * @param list - This parameter represents a list containing all the coordinates of the collision nodes. It is of string format as it is meant to be written into a text file.
	 * @return This method returns a modified list of node locations.
	 */
	public static List<String> modifylist(List<String> list) {

		newlist = new ArrayList<String>();

		if (list.size() > 23) {

			// Index represents which parts of the list will be added into the new list.
			// Example: For a list of size 30, every second location will be moved and used only
			int index = (list.size() / 23) + 1;

			for (int i = 0; i < list.size() - 1; i = i + index) {

				newlist.add(list.get(i));

			}
		} else {
			newlist = list;
		}
		return newlist;

	}

	/**
	 * This method takes a list of all the collision nodes found by our graphing algorithm after it has been modified, and it modifies the maps.txt file.
	 * It adds in all the waypoint information, and the start and end points for the route into a temporary file which becomes maps.txt at the end of the method.
	 * 
	 * @param Inputlist - This parameter represents a list of all the collision nodes after they were modified by the modifylist class.
	 * @throws IOException
	 */
	public static void userinput(List<String> Inputlist) throws IOException {

		list = Inputlist;

		// Reversing the list with collections. This is needed as the waypoints are added to the text file in a way where the last parts are first and vice versa,
		// Reversing the list fixes this problem, so the first waypoint is entered first in the text file.
		Collections.reverse(list);

		// location contains the start point, destination contains the end point.
		location = GreenLight.origin;

		destination = GreenLight.destination;

		oldFileName = "data/maps.txt";
		tmpFileName = "data/tmp_maps.txt";

		br = null;
		bw = null;

		// The following code reads the contents of maps.txt and moves it in the temporary file tmp_maps.txt
		// During the process, when certain strings are found it replaces them with another string which serves a vital purpose in generating the map.
		br = new BufferedReader(new FileReader(oldFileName));
		bw = new BufferedWriter(new FileWriter(tmpFileName));
		while ((line = br.readLine()) != null) {
			// The following for loop will add the waypoints (node locations) to the route, by adding the code needed to generate a waypoint to the file.
			for (int i = 0; i < list.size(); i++) {
				if (line.contains("var waypts = [];")) {
					line = line.replace("var waypts = [];",
							"var waypts = [];" + "\n" + "            waypts.push({" + "\n"
									+ "                 location: '" + list.get(i) + "'," + "\n"
									+ "                 stopover: true" + "\n" + "            });");
				}
			}
			// This adds the start location.
			if (line.contains("origin:")) {
				line = line.replace("origin:", "origin: '" + location + "',");
			}
			
			// This adds the end location.
			if (line.contains("destination:")) {
				line = line.replace("destination:", "destination: '" + destination + "',");
			}

			bw.write(line + "\n");
		}

		br.close();
		bw.close();

		// The maps.txt file which contains none of the input data is deleted, and the temporary file is renamed to maps.txt. 
		File oldFile = new File(oldFileName);
		oldFile.delete();

		File newFile = new File(tmpFileName);
		newFile.renameTo(oldFile);

	}

	/**
	 * This method cleans up the maps.txt file, by resetting the file to its original template, containing no input data.
	 * This is needed so a user can use our program multiple times.
	 * The framework for copying a file was refrenced from http://stacktips.com/tutorials/java/copying-contents-one-text-file-another-java.
	 * 
	 * @throws IOException
	 */
	public static void cleanup() throws IOException {
		
		// Resets maps.txt, emptying the file.
		PrintWriter writer = new PrintWriter("data/maps.txt");
		writer.print("");
		writer.close();

		FileInputStream input = null;
		OutputStream output = null;

		// Copying the contents of mapsref.txt which contains the basic template with no user inputs into maps.txt, so it can be used again.
		try {

			input = new FileInputStream(new File("data/mapsref.txt"));

			output = new FileOutputStream(new File("data/maps.txt"));

			byte[] bytes = new byte[1024];
			int bytesRead;

			while ((bytesRead = input.read(bytes)) > 0) {
				output.write(bytes, 0, bytesRead);
			}

		}

		finally {
			input.close();
			output.close();
		}

	}

	/**
	 * This method is used to read the contents of maps.txt into a string. The string contains html/javascript code which is used to generate the map.
	 * 
	 * @return This method returns a string containing html/javascript code which is displayed as a map with a direction panel.
	 * @throws IOException
	 */
	public static String genHtml() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("data/maps.txt"));

		// Reading the file, moving the contents into a string
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();

		} finally {
			br.close();
		}

	}

}