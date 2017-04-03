package cas2xb3.greenlight;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.omg.CORBA.portable.InputStream;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class GoogleMapsSample {
	private static String location;
	private static String destination;
	private static String waypoint;
	private static String oldFileName;
	private static String tmpFileName;
	private static BufferedWriter bw;
	private static BufferedReader br;
	private static BufferedReader br2;
	private static BufferedWriter bw2;
	private static String line;
	private static String line2;
	private static Scanner sc;
	private static List<String> list;
	private static List<String> templist;
	
	public static List modifylist(List list){
		
		templist = new ArrayList();
		
		int index = (list.size() / 23) + 1;
		
		int remove = list.size() - list.size() / index;
				
		int count = 0;
		
		count = 23 - remove;
		

		for (int i = 0; i < list.size() ; i = i + index){
			
			
			templist.add((String) list.get(i));
			
			if ( count != 0){
				
				templist.add((String) list.get(i + 1));
				
				count--;
				
			}
			
		}
		
		return templist;

	}
	
	public static void rank(List<Collision> list){
		
		List<Collision> dist = new ArrayList();
		
		for (int i = 0; i < list.size() - 1 ; i++){
			
			dist(list.get(i).getLat(),list.get(i).getLng(),list.get(i + 1).getLat(),list.get(i + 1).getLng());
			
		}
		
		List rank = dist;
		
		Collections.sort(rank);
		
		for (int i = 0; i < list.size() ; i++){
			
		}
		
	}
	
	
	public static void userinput(List Inputlist) throws IOException{
		
		//list = new ArrayList<String>();
		
		//list.add("Newton, Iowa");
		//list.add("Iowa City");
		
		list = Inputlist;
		
		Collections.reverse(list);
		
		sc = new Scanner(System.in);
		//System.out.println("Please enter a location");
		//location = sc.nextLine();
		location = "Vancouver";
		location = GeoCodingSample.getCoordinates(location);

		//System.out.println("Please enter a destination");
		//destination = sc.nextLine();
		destination = "Chicago";
		destination = GeoCodingSample.getCoordinates(destination);

		oldFileName = "data/maps.txt";
		tmpFileName = "data/tmp_maps.txt";

		br = null;
		bw = null;

		br = new BufferedReader(new FileReader(oldFileName));
		bw = new BufferedWriter(new FileWriter(tmpFileName));
		while ((line = br.readLine()) != null) {
			for(int i = 0; i < list.size() ; i++){
				if (line.contains("var waypts = [];")){
					line = line.replace("var waypts = [];",		
							"var waypts = [];" + "\n" + "            waypts.push({" + "\n"							
									+ "                 location: '" + list.get(i) + "'," + "\n"									
									+ "                 stopover: true" + "\n" + "            });");
				}				
			}
			if (line.contains("origin:")){
				line = line.replace("origin:", "origin: '" + location + "',");
			}
			if (line.contains("destination:")){
				line = line.replace("destination:", "destination: '" + destination + "',");
			}
			
			bw.write(line + "\n");
		}
		

		br.close();
		bw.close();

		File oldFile = new File(oldFileName);
		oldFile.delete();

		// And rename tmp file's name to old file name
		File newFile = new File(tmpFileName);
		newFile.renameTo(oldFile);

		// Set text file back to original
		   
	}
	
	public static void cleanup() throws IOException{
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
	
	public static double dist(double lat1, double lng1, double lat2, double lng2){
		//radius of earth in meters
		double r = 6371000;

		double dLat = Math.toRadians(lat1-lat2);
		double dLng = Math.toRadians(lng1-lng2);
		
		double t = Math.sin(dLat/2) * Math.sin(dLat/2) +
				   Math.cos(Math.toRadians((lat2))) * Math.cos(Math.toRadians(lat1)) * 
				   Math.sin(dLng/2) * Math.sin(dLng/2); 
		
		return r * (2 * Math.atan2(Math.sqrt(t), Math.sqrt(1-t)));
		
	}

	public static void main(String[] args) throws IOException {
		
		List nodes = new ArrayList();
		
		nodes.add("Seattle");
		
		nodes.add("Calgary");

		nodes.add("Edmonton");

		nodes.add("Regina");

		nodes.add("Saskatoon");

		nodes.add("Minneapolis");

		nodes.add("Winnipeg");

		nodes.add("Kenora");

		nodes.add("Thunder Bay");

		nodes.add("North Bay");

		nodes.add("Sudbury");

		nodes.add("Barrie");

		nodes.add("Toronto");

		nodes.add("Buffalo");

		nodes.add("Albany");

		nodes.add("New York");

		nodes.add("Boston");

		nodes.add("Montreal");

		nodes.add("Sarnia");

		nodes.add("Detroit");
		
		nodes.add("Colombus");
		
		nodes.add("St. Louis");

		nodes.add("Des Moines");
		
		nodes.add("Cedar Rapids");
		
		nodes.add("Iowa City");
		
		nodes.add("Sioux City");
		

		nodes = modifylist(nodes);
		

		userinput(nodes);

		
		final Browser browser = new Browser();
		BrowserView browserView = new BrowserView(browser);

		JFrame frame = new JFrame("maps.html");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(browserView, BorderLayout.CENTER);
		frame.setSize(900, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		browser.loadHTML(genHtml());

		cleanup();
		
	
		//System.out.println(23 / 23);
		
		//System.out.println(24 / 2);
		
		//System.out.print(modifylist(nodes).size());
		
		//System.out.println(modifylist(nodes));
	}

}
