package jpl.ch24.ex02;

import java.util.Currency;
import java.util.Locale;

public class Table {

	public static void main(String[] args) {
		
		System.out.println("カナダ、中国、アメリカ、フランス、イタリア、日本");
		System.out.println("に対する各ロケールでの各通貨に対する通貨記号を表示します。");
		
		Locale[] locales = new Locale[6];
		Currency[] currencies = new Currency[6];
		locales[0] = Locale.CANADA;
		locales[1] = Locale.CHINA;
		locales[2] = Locale.US;
		locales[3] = Locale.FRANCE;
		locales[4] = Locale.ITALY;
		locales[5] = Locale.JAPAN;
		
		System.out.print("     ");
		for(int i=0; i<6; i++){
			currencies[i] = Currency.getInstance(locales[i]);
			System.out.print(locales[i].getCountry()+"   ");
		}
		System.out.println();
		for(int i=0; i<6; i++){
			System.out.print(currencies[i].getCurrencyCode() + "  ");
			for(int j=0; j<6; j++){
				System.out.print(currencies[i].getSymbol(locales[j])+"  ");
				if(currencies[i].getSymbol(locales[j]).length()==1)System.out.print(" ");
			}
			System.out.println();
		}
		
		
	}

}
