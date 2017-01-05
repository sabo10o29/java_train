package gui.ex14;

import java.util.prefs.Preferences;
import java.awt.Color;
import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.prefs.BackingStoreException;
 
public class PrefsParameter {
    private Preferences prefs;
    private static String KEY = "Parameter";
    
 
    public PrefsParameter() {
        prefs = Preferences.userNodeForPackage(this.getClass()); // (1)
    }
 
    public void save(Parameter p) throws IOException {
    		try {
            System.out.println("Save the name: " + this.KEY);
            prefs.put("Font", p.font_name);
            prefs.put("FontColor", getColorName(p.font_color));
            prefs.put("BackColor", getColorName(p.back_color));
            prefs.putDouble("FontSize", p.font_multi);
            prefs.putDouble("X", p.window_x);
            prefs.putDouble("Y", p.window_y);
            prefs.putDouble("Width", p.width);
            prefs.putDouble("Height", p.height);
            prefs.flush();                                       // (2)
        } catch (BackingStoreException ex) {
            ex.printStackTrace();
        }
    }
 
    public Parameter load() throws ClassNotFoundException, IOException {
    		Parameter tmp 	= new Parameter();
    		tmp.font_name 	= prefs.get("Font", "Arial");
        tmp.font_color 	= getColorObject(prefs.get("FontColor", "BLACK"));
        tmp.back_color  = getColorObject(prefs.get("BackColor", "WHITE"));
        tmp.font_multi  = prefs.getDouble("FontSize", 1.0);
        tmp.window_x    = prefs.getDouble("X", 0.0);
        tmp.window_y    = prefs.getDouble("Y", 0.0);
        tmp.width   		= prefs.getDouble("Width", 1000.0);
        tmp.height   	= prefs.getDouble("Height", 500.0);
        return tmp;
        
    }
    /*
    public static void main(String[] args) {
        Prefs test = new Prefs();
        if (args.length > 0) {
            test.save(args[0]);
        } else {
            test.load();
        }
    }*/
    
    public String getColorName(Color color){
		if(color.equals(Color.black))		return "BLACK";
		else if(color.equals(Color.white))	return "WHITE";
		else if(color.equals(Color.red))	return "RED";
		else if(color.equals(Color.green))	return "GREEN";
		else if(color.equals(Color.blue))	return "BLUE";
		else if(color.equals(Color.yellow))	return "YELLOW";
		else if(color.equals(Color.pink))	return "PINK";
		else if(color.equals(Color.cyan))	return "CYAN";
		else{return "BLACK";}
	}
    
    public Color getColorObject(String color){
		if("BLACK".equals(color))		return Color.black;
		else if("WHITE".equals(color))	return Color.white;
		else if("RED".equals(color))	return Color.red;
		else if("GREEN".equals(color))	return Color.green;
		else if("BLUE".equals(color))	return Color.blue;
		else if("YELLOW".equals(color))	return Color.yellow;
		else if("PINK".equals(color))	return Color.pink;
		else if("CYAN".equals(color))	return Color.cyan;
		else{return Color.BLACK;}
    }
    
    
}
