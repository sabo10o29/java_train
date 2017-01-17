package jpl.ch16.gui;

import javax.swing.JPanel;

public class InstanceSettingWindow extends JPanel{
	private String classname;
	
	InstanceSettingWindow(String classname){
		//引数として渡されたクラス名をもとにインスタンスを生成するための設定画面
		System.out.println(classname+"のインスタンスを生成");
		
	}
	
	
	
}
