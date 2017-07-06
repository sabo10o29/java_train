package jpl.ch24.ex01;

import java.io.File;
import java.util.ResourceBundle;

public class GlobalHello {

	public static void main(String[] args) {

		// ListResourceBundle
		ResourceBundle bundle = ResourceBundle.getBundle("jpl.ch24.ex01.GlobalRes_ja");
		System.out.println(bundle.getString(GlobalRes.GOODBYE));

		// .properties
		bundle = ResourceBundle.getBundle("jpl.ch24.ex01.GlobalRes_it");
		System.out.println(bundle.getString(GlobalRes.GOODBYE));

		// ResourceBundle
		GrobalRes_fr bundle_fr = new GrobalRes_fr();
		System.out.println(bundle_fr.getString(GlobalRes.GOODBYE));

	}

}
