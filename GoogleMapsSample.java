import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class GoogleMapsSample {
   public static void main(String[] args) throws IOException {
	   
	   Scanner in = new Scanner(System.in);
	   
	   System.out.println("Enter the origin:");
	   
	   String origin = in.nextLine();
	   
	   System.out.println("Enter the destinatin:");

	   String destination = in.nextLine();

	   FileInputStream input = null;
       FileOutputStream output = null;
       BufferedWriter bw = null;
       FileWriter fw = null;
       
       try {
           input = new FileInputStream("maps.txt");
           output = new FileOutputStream("maps.html");
           byte[] buffer = new byte[1024];
           int length;
           
           /*
           Scanner sc = new Scanner("maps.txt");
           fw = new FileWriter("maps.txt");
           bw = new BufferedWriter(fw);
           
           while (sc.hasNextLine()){
        	   
               String line = sc.nextLine();
               
               if(line.contains("origin")){
            	   
            	   line = line.replaceAll(regex, replacement)
               }
           }*/
                      
           while ((length = input.read(buffer)) > 0) {
              output.write(buffer, 0, length);
              
           } 
           
           input.close();
           output.close();
        } catch(IOException ioe) {
           System.out.println("Failure");
        }
       
       final Browser browser = new Browser();
       BrowserView browserView = new BrowserView(browser); 
       
       JFrame frame = new JFrame("maps.html");
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.add(browserView, BorderLayout.CENTER);
       frame.setSize(900, 500);
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);

       browser.loadURL("file:///C:/Users/Gundeep/ProjectSpace/HelloJxBrowser/maps.html");
   
   }
   
}