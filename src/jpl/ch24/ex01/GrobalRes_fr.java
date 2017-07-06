package jpl.ch24.ex01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class GrobalRes_fr extends ResourceBundle {

	@Override
	protected Object handleGetObject(String key) {
		if ("HELLO".equals(key))
			return "Bonjour";
		else if ("GOODBYE".equals(key))
			return "Au revoir";
		return null;
	}

	@Override
	public Enumeration<String> getKeys() {
		ArrayList<String> list = new ArrayList<>();
		list.add("HELLO");
		list.add("GOODBYE");
		return Collections.enumeration(new ArrayList<String>(list));
	}

}
