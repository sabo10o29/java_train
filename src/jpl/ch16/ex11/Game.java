package jpl.ch16.ex11;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	public Boolean battle(int hand){
		Random rand = new Random();
		int cp = rand.nextInt(3);
		System.out.println("CP " + handName(cp) + " vs Player " + handName(hand));
		if(hand == cp) return null;
		else if(hand == 0 && cp == 1) return true;
		else if(hand == 1 && cp == 2) return true;
		else if(hand == 2 && cp == 0) return true;
		else return false;
	}
	
	public String handName(int i){
		switch(i){
		case 0:
			return "Rock";
		case 1:
			return "Scissors";
		case 2:
			return "Paper";
		}
		return "Unknown";
	}
	
	
	public static void main(String[] args) {
		String name = "jpl.ch16.ex11.StubbornPlayer";
		Game game = new Game();
		
		try{
			PlayerLoader loader = new PlayerLoader();
			Class<? extends Player> classOf = loader.loadClass(name).asSubclass(Player.class);
			Player player = classOf.newInstance();
			player.play(game);
			System.out.println("Winning percentage " + player.getWinPercent());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	

}
