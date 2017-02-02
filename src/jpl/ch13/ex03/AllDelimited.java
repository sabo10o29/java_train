package jpl.ch13.ex03;

public class AllDelimited {
	
	public static String[] delimitedString(String from, char start, char end){
		String[] results = new String[(int)(from.length()/2.0)];
		int count = 0; 
		Boolean judge = false;
		
		do{
			int startPos = from.indexOf(start);
			int endPos = from.indexOf(end);
			if(startPos==-1){
				judge = false;
			}else if(endPos==-1){
				judge = false;
			}else if(startPos>endPos){
				from = from.replaceFirst(String.valueOf(start), "");
				from = from.replaceFirst(String.valueOf(end), "");
				judge = true;
			}else{
				results[count] = from.substring(startPos, endPos+1);
				count++;
				from = from.replaceFirst(String.valueOf(start), "");
				from = from.replaceFirst(String.valueOf(end), "");
				judge = true;
			}
			
		}while(judge);
		
		return results;
	}
	
	public static void main(String[] args){
		String[] str = AllDelimited.delimitedString("<test>aiu<hoge>eoeo",'<','>');
		for(int i=0;i<str.length;i++)
		System.out.println(str[i]);
	}
	
	
	
}
