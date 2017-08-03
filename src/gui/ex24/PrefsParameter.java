package gui.ex24;

import java.util.prefs.Preferences;
import java.awt.Color;
import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.prefs.BackingStoreException;

/**
 * 現在の全パラメータを保存するクラス
 * クロック起動直後にリファレンスから値を取得する
 * 取得できない場合にはデフォルトのパラメータを返す。
 * @author YoshikazuMurase
 *
 */
public class PrefsParameter {
	private Preferences prefs;
	private static String KEY = "Parameter";

	public PrefsParameter() {
		prefs = Preferences.userNodeForPackage(this.getClass()); // (1)
	}

	public void save(Parameter p) throws IOException {
		try {
			System.out.println("Save the name: " + this.KEY);
			prefs.put("Font", p.getFname());
			prefs.put("FontColor", getColorName(p.getFcolor()));
			prefs.put("BackColor", getColorName(p.getColor()));
			prefs.putDouble("FontSize", p.getFsize());
			prefs.putDouble("X", p.getX());
			prefs.putDouble("Y", p.getY());	
			prefs.putDouble("Width", p.getWidth());
			prefs.putDouble("Height", p.getHeight());
			prefs.flush(); // (2)
		} catch (BackingStoreException ex) {
			ex.printStackTrace();
		}
	}

	public Parameter load() throws ClassNotFoundException, IOException {
		Parameter tmp = new Parameter();
		tmp.setFname(prefs.get("Font", "Arial"));
		tmp.setFcolor(getColorObject(prefs.get("FontColor", "BLACK")));
		tmp.setColor(getColorObject(prefs.get("BackColor", "WHITE")));
		tmp.setFsize(prefs.getDouble("FontSize", 200.0));
		tmp.setX(prefs.getDouble("X", 0.0));
		tmp.setY(prefs.getDouble("Y", 0.0));
		return tmp;

	}
	
	/**
	 * セーブデータをクリアする際に使用するメソッド
	 * @throws IOException
	 */
	public void clear() throws IOException {
		Parameter tmp = new Parameter();
		save(tmp);
	}

	public String getColorName(Color color) {
		if (color.equals(Color.black))
			return "BLACK";
		else if (color.equals(Color.white))
			return "WHITE";
		else if (color.equals(Color.red))
			return "RED";
		else if (color.equals(Color.green))
			return "GREEN";
		else if (color.equals(Color.blue))
			return "BLUE";
		else if (color.equals(Color.yellow))
			return "YELLOW";
		else if (color.equals(Color.pink))
			return "PINK";
		else if (color.equals(Color.cyan))
			return "CYAN";
		else {
			return "BLACK";
		}
	}

	public Color getColorObject(String color) {
		if ("BLACK".equals(color))
			return Color.black;
		else if ("WHITE".equals(color))
			return Color.white;
		else if ("RED".equals(color))
			return Color.red;
		else if ("GREEN".equals(color))
			return Color.green;
		else if ("BLUE".equals(color))
			return Color.blue;
		else if ("YELLOW".equals(color))
			return Color.yellow;
		else if ("PINK".equals(color))
			return Color.pink;
		else if ("CYAN".equals(color))
			return Color.cyan;
		else {
			return Color.BLACK;
		}
	}

}
