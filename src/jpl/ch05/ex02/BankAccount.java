package jpl.ch05.ex02;

public class BankAccount {
	private long number;			//口座番号
	private long balance;			//現在の残高（単位は円）
	private Action lastAct;			//最後に行われた処理
	private History hist = new History();			//過去の処理を10個記憶するクラス
	
	//コンストラクタ
	BankAccount(long _number,long _balance){
		this.number = _number;
		this.balance = _balance;
	}
	
	//最後に行われた処理を保存するクラス
	public class Action {
		private String act;			//処理名を保存
		private long amount;		//処理に生じた金額
		private long _balance;
		Action(String act, long amount){
			this.act = act;
			this.amount = amount;
			_balance = balance;
			hist.add(this); 		//Historyに自分自身を追加する
		}
		public String toString(){	//処理名と金額
			//identify our enclosing account
			return number + ": " + act + " " + amount + "(balance: " + _balance + ")";
		}
	}
	
	//Actionを保存するクラス
	public class History{
		private Action[] action = new Action[10];
		
		public void add(Action _action){
			for(int i=8; i>=0; i--){
				this.action[i+1] = this.action[i];
			}
			this.action[0] = _action;
		}
		
		public Action next(){
			Action tmp = this.action[0];
			for(int i=1; i<=8; i++){
				this.action[i-1] = this.action[i];
			}
			this.action[9] = null;
			return tmp;
		}
		
		
	}
	
	//Historyクラスのインスタンスを返すメソッド
	public History history(){
		return this.hist;
	}
	
	
	//預金
	public void deposit(long amount){
		balance += amount;
		lastAct = new Action("deposit",amount);
	}
	
	//引き出し
	public void withdraw(long amount){
		balance -= amount;
		lastAct = new Action("withdraw",amount);
	}
	
	//送金処理
	public void transfer(BankAccount other, long amount){
		other.withdraw(amount);									//他の口座から引き出し
		deposit(amount);										//自口座へ入金
		lastAct = this.new Action ("transfer",amount);			//最後の処理を保存（処理、金額など）
		other.lastAct = other.new Action("transfer", amount);	//他口座にも処理を保存
	}
	
	public static void main(String args[]){
		//アカウントの作成
		BankAccount accountA = new BankAccount(1,100);
		BankAccount accountB = new BankAccount(2,50000000);
		//預金
		accountA.deposit(1000000);					//1000100
		//引き出し
		accountA.withdraw(500000);					//500100
		//送金処理
		accountA.transfer(accountB, 300000);		//800100
		accountA.transfer(accountB, 100000);		//800100
		//アカウントAの処理履歴の確認
		History now_history = accountA.history();
		Object obj = now_history.next();
		while(obj!=null){
			System.out.println(obj.toString());
			obj = now_history.next();
		}
		
	}
	
}
