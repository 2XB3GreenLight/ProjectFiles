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
 * 
 * @author Gundeep
 *
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
	private static List<String> templist;

	public static List<String> modifylist(List<String> list) {

		templist = new ArrayList<String>();

		if (list.size() > 23) {

			int index = (list.size() / 23) + 1;

			for (int i = 0; i < list.size() - 1; i = i + index) {

				templist.add(list.get(i));

			}
		} else {
			templist = list;
		}
		return templist;

	}

	public static void userinput(List<String> Inputlist) throws IOException {

		list = Inputlist;

		Collections.reverse(list);

		location = GreenLight.origin;

		destination = GreenLight.destination;

		oldFileName = "data/maps.txt";
		tmpFileName = "data/tmp_maps.txt";

		br = null;
		bw = null;

		br = new BufferedReader(new FileReader(oldFileName));
		bw = new BufferedWriter(new FileWriter(tmpFileName));
		while ((line = br.readLine()) != null) {
			for (int i = 0; i < list.size(); i++) {
				if (line.contains("var waypts = [];")) {
					line = line.replace("var waypts = [];",
							"var waypts = [];" + "\n" + "            waypts.push({" + "\n"
									+ "                 location: '" + list.get(i) + "'," + "\n"
									+ "                 stopover: true" + "\n" + "            });");
				}
			}
			if (line.contains("origin:")) {
				line = line.replace("origin:", "origin: '" + location + "',");
			}
			if (line.contains("destination:")) {
				line = line.replace("destination:", "destination: '" + destination + "',");
			}

			bw.write(line + "\n");
		}

		br.close();
		bw.close();

		File oldFile = new File(oldFileName);
		oldFile.delete();

		File newFile = new File(tmpFileName);
		newFile.renameTo(oldFile);

	}

	public static void cleanup() throws IOException {
		Collections.reverse(list);

		PrintWriter writer = new PrintWriter("data/maps.txt");
		writer.print("");
		writer.close();

		FileInputStream input = null;
		OutputStream output = null;

		try {

			/* FileInputStream to read streams */
			input = new FileInputStream(new File("data/mapsref.txt"));

			/* FileOutputStream to write streams */
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

	public static String genHtml() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("data/maps.txt"));

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

