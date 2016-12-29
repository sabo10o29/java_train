package jpl.ch10.ex03;

public class WorkingDay {
	
	enum Week {
		SUNDAY,
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY
	}
	
	public static void main(String[] args) {
		if(getWorkDayIf(Week.FRIDAY)){
			System.out.println("Working Day");
		}else{
			System.out.println("Not Working Day");
		}
		
		if(getWorkDaySwitch(Week.FRIDAY)){
			System.out.println("Working Day");
		}else{
			System.out.println("Not Working Day");
		}
		
		
		
		if(getWorkDayIf(Week.SUNDAY)){
			System.out.println("Working Day");
		}else{
			System.out.println("Not Working Day");
		}
		
		if(getWorkDaySwitch(Week.SUNDAY)){
			System.out.println("Working Day");
		}else{
			System.out.println("Not Working Day");
		}
		
	}
	
	public static boolean getWorkDayIf(Week test){
		if(test==Week.SUNDAY){
			return false;
		}else if(test==Week.MONDAY){
			return true;
		}else if(test==Week.TUESDAY){
			return true;
		}else if(test==Week.WEDNESDAY){
			return true;
		}else if(test==Week.THURSDAY){
			return true;
		}else if(test==Week.FRIDAY){
			return true;
		}else if(test==Week.SATURDAY){
			return false;
		}else{
			return false;
		}
	}
	public static boolean getWorkDaySwitch(Week test){
		switch(test){
			case SUNDAY: 	return false;
			case MONDAY: 	return true;
			case TUESDAY: 	return true;
			case WEDNESDAY: return true;
			case THURSDAY: 	return true;
			case FRIDAY: 	return true;
			case SATURDAY: 	return false;
			default: 		return false;
		}
	}

}
