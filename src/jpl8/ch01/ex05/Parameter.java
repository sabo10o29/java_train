package jpl8.ch01.ex05;

import java.awt.Color;
import java.awt.Font;

//時計表示に関するフィールド値を持つクラス
public class Parameter implements Cloneable {

	private String fname = "Arial"; // 初期フォント
	private Color fcolor = Color.black; // 初期フォントカラー
	private Color color = Color.white; // 背景カラー
	private double fsize = 200; // 初期フォントサイズ

	// ゲッター
	public String getFname() {
		return fname;
	}

	public Color getFcolor() {
		return fcolor;
	}

	public Color getColor() {
		return color;
	}

	public double getFsize() {
		return fsize;
	}

	public double getWidth() {
		return fsize * 5;
	}

	public double getHeight() {
		return fsize * 2;
	}

	// セッター
	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setFcolor(Color fcolor) {
		this.fcolor = fcolor;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setFsize(double fsize) {
		this.fsize = fsize;
	}

	public Font getFont() {
		return new Font(fname, Font.BOLD, (int) fsize);
	}

	public void setFont(Font font) {
		fname = font.getFontName();
		fsize = font.getSize();
	}

	@Override
	public Parameter clone() {
		Parameter b = null;
		try {
			b = (Parameter) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
}
