package interpret;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public abstract class StepBasePanel extends JPanel{
	
	//
	
	public void putStringToJLabel(String[] str){
		
	}
	
	public void paintComponent(Graphics g){
	    Graphics2D g2 = (Graphics2D)g;

	    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
	                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    
	    paintPanel(g2);

	    //g2.drawString("Hello Java2D", 100, 100);

	}
	
	public abstract void paintPanel(Graphics2D g2);
	

}
