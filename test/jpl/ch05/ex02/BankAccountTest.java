package jpl.ch05.ex02;

import static org.junit.Assert.*;
import org.junit.Test;
import StdCap.StdoutCapture;

public class BankAccountTest {

	@Test
	public void testBankAccount() {
		BankAccount test = new BankAccount(1,1000);
		assertEquals(test.toString(),"1: 1000(balance)");
	}

	@Test
	public void testHistory() {
		//ヒストリークラスの中はHistoryTestで実施
		BankAccount test = new BankAccount(1,1000);
		test.deposit(100);
		assertNotNull(test.history());
	}

	@Test
	public void testDeposit() {
		BankAccount test = new BankAccount(1,1000);
		test.deposit(500);
		assertEquals(test.toString(),"1: 1500(balance)");
	}

	@Test
	public void testWithdraw() {
		BankAccount test = new BankAccount(1,1000);
		test.withdraw(500);
		assertEquals(test.toString(),"1: 500(balance)");
	}

	@Test
	public void testTransfer() {
		BankAccount A = new BankAccount(1,1000);
		BankAccount B = new BankAccount(2,1000);
		A.transfer(B, 200);;
		assertEquals(A.toString(),"1: 1200(balance)");
		assertEquals(B.toString(),"2: 800(balance)");
	}

	@Test
	public void testMain() {
		String[] expected = new String[] {
				"1: transfer 100000(balance: 900100)",
				"1: deposit 100000(balance: 900100)",
				"1: transfer 300000(balance: 800100)",
				"1: deposit 300000(balance: 800100)",
				"1: withdraw 500000(balance: 500100)",
				"1: deposit 1000000(balance: 1000100)"
		};
        
        StdoutCapture sc = new StdoutCapture();
        sc.start();
        
        BankAccount.main(new String[0]);
        
        sc.stop();
        sc.assertEquals(expected);
	}

}
