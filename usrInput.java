package cas2xb3.greenlight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class usrInput {
	public static void main(String args[]) throws IOException{
		   Scanner sc = new Scanner(System.in);
		   System.out.println("Please enter a location");
		   String location = sc.nextLine();
		   
		   System.out.println("Please enter a destination");
		   String destination = sc.nextLine();
		   
		   String oldFileName = "data/maps.txt";
		   String tmpFileName = "data/tmp_maps.txt";
		   
		   BufferedReader br = null;
		   BufferedWriter bw = null;
		   
		   br = new BufferedReader(new FileReader(oldFileName));
	       bw = new BufferedWriter(new FileWriter(tmpFileName));
	       String line;
	       while ((line = br.readLine()) != null) {
	           if (line.contains( "origin:"))
	               line = line.replace("origin:" , "origin: '" + location + "',");
	           if (line.contains( "destination:"))
	        	   line = line.replace("destination:" , "destination: '" + destination + "',"); 
	          bw.write(line+"\n");}

		   br.close();
		   bw.close();
		   
		   File oldFile = new File(oldFileName);
		   oldFile.delete();

		   // And rename tmp file's name to old file name
		   File newFile = new File(tmpFileName);
		   newFile.renameTo(oldFile);
		   
		   GoogleMapsSample.main(args);
		   
		   // Set text file back to original
		   
		   String alteredFile = "data/maps.txt";
		   String tmpFile = "data/tmp_maps.txt";
		   
		   BufferedReader br2 = null;
		   BufferedWriter bw2 = null;
		   
		   br2 = new BufferedReader(new FileReader(oldFileName));
	       bw2 = new BufferedWriter(new FileWriter(tmpFileName));
	       String line2;
	       while ((line2 = br2.readLine()) != null) {
	           if (line2.contains( "origin: '" + location + "',"))
	               line2 = line2.replace("origin: '" + location + "'," , "origin:");
	           if (line2.contains( "destination: '" + destination + "',"))
	        	   line2 = line2.replace("destination: '" + destination + "'," , "destination:"); 
	          bw2.write(line2+"\n");}

		   br2.close();
		   bw2.close();
		   
		   File alteredF = new File(alteredFile);
		   alteredF.delete();

		   // And rename tmp file's name to old file name
		   File resetFile = new File(tmpFile);
		   resetFile.renameTo(alteredF);
		   
		   
	}

}
