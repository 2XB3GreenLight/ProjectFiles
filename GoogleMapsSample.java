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
	   
	   FileInputStream input = null;
       FileOutputStream output = null;

       try {
           input = new FileInputStream("maps.txt");
           output = new FileOutputStream("maps.html");
           byte[] buffer = new byte[1024];
           int length;
                      
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
