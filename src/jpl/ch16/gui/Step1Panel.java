package jpl.ch16.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Step1Panel extends StepBasePanel{
	
	Step1Panel(){
		
	}

	@Override
	public void paintPanel(Graphics2D g2) {
		Font font = new Font("Arial", Font.BOLD, 20);
		g2.setFont(font);
		g2.drawString("Interpret へようこそ", 30, 60);
		font = new Font("Arial", Font.PLAIN, 15);
		g2.setFont(font);
		g2.drawString("このプログラムでは任意のクラスの", 40, 100);
		g2.drawString("インスタンスを生成します。", 40, 120);
		
		g2.drawString("生成を中止して、実行中のプログラムを終了する", 40, 220);
		g2.drawString("場合には ”Cancel” ボタンをクリックして下さい。", 40, 240);
		g2.drawString("続行する場合には ”Next” ボタンをクリックして", 40, 260);
		g2.drawString("下さい。", 40, 280);
	}
	
}
