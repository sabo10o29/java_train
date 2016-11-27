package Hello;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("HelloWorld");
		String filename = "java_train¥src¥Hello¥POCKC__.TTF";
		String filename1 = "POCKC__.TTF";
		String filename2 = "./src/Hello/a.ttf";
		
		
		
		
		try {
        	InputStream myStream = new BufferedInputStream(new FileInputStream(filename2));
        	Font font = Font.createFont(Font.TRUETYPE_FONT, myStream);
            //is = getClass().getResourceAsStream(filename);
            //font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
            
            //is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        
	}

}
