package jpl.ch06.ex03;

interface Verbose {
	enum ENUM {
		SILENT(0),
		TERSE(1),
		NORMAL(2),
		VERBOSE(3);
		
		public final int value;
		
		ENUM(int _value){
			this.value = _value;
		}
		
		public String toString(){
			return String.valueOf(this.value);
		}	
	}
	
	void setVerbosity(int level);
	int getVerbosity();
	
}
