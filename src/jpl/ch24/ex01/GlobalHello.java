package jpl.ch24.ex01;

import java.util.ResourceBundle;

public class GlobalHello {

	public static void main(String[] args){
		
		//ファミリ名：GlobalRes
		ResourceBundle res = ResourceBundle.getBundle("jpl.ch24.ex01.GlobalRes_ja");
		String msg;
		if(args.length>0){
			msg = res.getString(GlobalRes.GOODBYE);
		}else{
			msg = res.getString(GlobalRes.HELLO);
		}
		System.out.println(msg);
		
		ResourceBundle res2 = ResourceBundle.getBundle("jpl.ch24.ex01.GlobalRes_it");
		System.out.println(res2.getString(GlobalRes.GOODBYE));
		
		
	}
	
}
