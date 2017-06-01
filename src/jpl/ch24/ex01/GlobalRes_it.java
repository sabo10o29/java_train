package jpl.ch24.ex01;

import java.util.ListResourceBundle;

public class GlobalRes_it extends ListResourceBundle{
	
	private final static String resources[][] = {
	        {GlobalRes.GOODBYE, "Ciao"},
	        {GlobalRes.HELLO, "Ciao"}
	};
	
	@Override
	protected Object[][] getContents() {
		return resources;
	}
}
