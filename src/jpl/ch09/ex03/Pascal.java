package jpl.ch09.ex03;

//特になし
public class Pascal {

	int[][] array;
	int depth;
	
	public static void main(String[] args) {
		Pascal test = new Pascal(20);
		test.showPascal();
	}
	
	public Pascal(int depth){
		this.depth = depth;
		array = new int[depth][];
		for(int i=0;i<depth;i++){
			array[i] = new int[i+1];
		}
		
		for(int i=0;i<depth;i++){
			for(int j=0;j<=i;j++){
				if(j==0||j==i){
					array[i][j] = 1; 
				}else{
					array[i][j] = array[i-1][j-1]+array[i-1][j];
				}
			}
		}
		
	}
	
	public void showPascal(){
		for(int i=0;i<depth;i++){
			for(int j=0;j<=i;j++){
				System.out.print(array[i][j] + " ");
			}
			System.out.println("");
		}
	}

	
}
