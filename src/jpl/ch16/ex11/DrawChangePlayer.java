package jpl.ch16.ex11;

import java.util.Random;

public class DrawChangePlayer extends Player{

	public static final int MAX_GAME_SET = 10000;
	
	@Override
	public void play(Game game) {
		int[] result = new int[MAX_GAME_SET];
		int hand = 0;
		int count = 0;
		Boolean x = null;
		
		while(count != MAX_GAME_SET){
			
			Random rand = new Random();
			if(count!=0 && x == null){	//あいこのとき
				int n = rand.nextInt(3);
				while(hand == n){
					n = rand.nextInt(3);
				}
				hand = n;
			}else{
				hand = rand.nextInt(3);
			}
			x = game.battle(hand);
			if(x != null){
				result[count] = x ? 1 : 0;
				count ++;
			}
		}
		
		count = 0;
		for(int i : result){
			if(i==1)
				count++;
		}
	 setWinPercent(count*100.0/MAX_GAME_SET);
	}

}
