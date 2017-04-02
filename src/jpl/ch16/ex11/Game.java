package jpl.ch16.ex11;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	private int count;
	private Boolean beforeResult;
	Random rand = new Random();
	int cp;
	
	public Boolean battle(int hand){
		
		if(count!=0 && beforeResult == null){	//あいこのとき
			
		}else{
			cp = rand.nextInt(3);
		}
		count ++;
		
		System.out.println("CP " + handName(cp) + " vs Player " + handName(hand));
		if(hand == cp) beforeResult = null;
		else if(hand == 0 && cp == 1) beforeResult = true;
		else if(hand == 1 && cp == 2) beforeResult = true;
		else if(hand == 2 && cp == 0) beforeResult = true;
		else beforeResult = false;
		
		return beforeResult;
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
//		String name = "jpl.ch16.ex11.StubbornPlayer";
		String name = "jpl.ch16.ex11.DrawChangePlayer";
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
