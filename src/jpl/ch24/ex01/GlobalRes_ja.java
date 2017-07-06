package jpl.ch24.ex01;

import java.util.ListResourceBundle;

public class GlobalRes_ja extends ListResourceBundle {

	private final static String resources[][] = { { GlobalRes.GOODBYE, "さようなら" }, { GlobalRes.HELLO, "こんにちは" } };

	@Override
	protected Object[][] getContents() {
		return resources;
	}

}
