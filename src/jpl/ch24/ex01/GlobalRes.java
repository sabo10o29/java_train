package jpl.ch24.ex01;

import java.util.ListResourceBundle;

public class GlobalRes extends ListResourceBundle {

	public static final String GOODBYE = "GOODBYE";
	public static final String HELLO = "HELLO";

	private final static String resources[][] = { { GlobalRes.GOODBYE, "good-by" }, { GlobalRes.HELLO, "hello" } };

	@Override
	protected Object[][] getContents() {
		return resources;
	}

}
