package jpl.ch22.ex05;

import java.util.Random;

/**
 * さいころの個数とシミュレートする”投げる”回数を引数にとって
 * 理論的確率と実際の確率を表示するプログラム
 * さいころの総和ごとに確率を表示する
 * @author YoshikazuMurase
 *
 */
public class DiceProbability {
	
	public static void showProbability(int numDice, int numFace, int numTrials){
		
		System.out.println("理論的確率");
		double[] result1 = DiceProbability.theoreticProbability(numDice, numFace);
		for(int i = numDice; i<=numDice*6; i++){
			System.out.println(i + ": " + result1[i]);
		}
		
		System.out.println("さいころを振ったときの確率");
		double[] result2 = DiceProbability.rollDiceProbability(numDice, numFace, numTrials);
		for(int i = numDice; i<=numDice*6; i++){
			System.out.println(i + ": " + result2[i]);
		}
		
	}
	
	//振ったときの確率
	static double[] rollDiceProbability(int numDice, int numFace, int numTrials){
		
		double[] result = new double[(numDice*6)+1];
		for(int i=0; i<numTrials; i++){
			int val =0;
			for(int j = 0; j<numDice; j++){
				val += (new Random().nextInt(numFace) + 1);
			}
			result[val]++;
		}
		
		for(int i=numDice; i<=numDice*6; i++){
			result[i] = result[i]/numTrials;
		}
		
		return result;
	}
	
	//さいころの個数、面から確率を求めるメソッド
	static double[] theoreticProbability(int numDice, int numFace){
		double[] result = new double[1+numDice*6];
		int N = numDice;	//さいころの個数
		int K = numFace;			//さいころの面の数
		
		for(int C = numDice; C<=numDice*6; C++){
			double val = 0;		//
			for(int i= 0; i <= Math.floor(((C-N)/K)); i++){
				val += Math.pow(-1, i)*calcCombination(N, i)*calcCombination((C-K*i-1), (N-1));
			}
			result[C] = val/Math.pow(K, N);
		}
		
		return result;
		
	}
	
	//コンビネーションを求めるメソッド
	public static double calcCombination( int n, int m ) {
	    if( n < m || m < 0 ) {
	        throw new IllegalArgumentException( "引数の値が不正です ( n : " + n + ", m : " + m + ")" );
	    }
	    double c = 1;
	    m = ( n - m < m ? n - m : m );
	    for( int ns = n - m + 1, ms = 1; ms <= m; ns ++, ms ++ ) {
	        c *= ns;
	        c /= ms;
	    }
	    return c;
	}
	
	public static void main(String[] args) {
		int numDice = 2;
		int numTrials = 10000;
		int numFace = 6;
		DiceProbability.showProbability(numDice, numFace, numTrials);
	}

}
