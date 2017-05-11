package jpl.ch22.ex06;

import java.util.Random;

/**
 * nextGaussianを大量に呼び出してその値の数のヒストグラムを作成する
 * ヒストグラムは＊で表示する（１００で＊カウント）
 * @author YoshikazuMurase
 *
 */
public class TestGaussian {
	
	public static void ShowHist(int numTrials){
		int[] hist = new int[1+100*6];
		
		Random r = new Random();
		for(int i=0; i<numTrials; i++){
			double val = r.nextGaussian();
			if(val<=3 && val>=-3){			//−３〜＋３の値を切り取り
				int j = (int)(300+val*100);	//0-600		300シフト
				hist[j]++;
			}
		}
		
		for(int i=0; i<=600; i++){
			int j = (int)(hist[i]/10000);
			for(j = 0; j<=(int)(hist[i]/10000); j++ ){
				System.out.print("*");
			}
			System.out.println();
		}
		
	}
	
	
	public static void main(String[] args) {
		TestGaussian.ShowHist(100000000);
	}

}
