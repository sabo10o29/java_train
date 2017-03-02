package jpl.ch16.ex11;

public abstract class Player {
	String name;
	private double winPercent;
	public abstract void play(Game game);
	public double getWinPercent(){
		return winPercent;
	}
	public void setWinPercent(double score){
		winPercent = score;
	}
}
