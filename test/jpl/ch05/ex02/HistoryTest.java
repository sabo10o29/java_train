package jpl.ch05.ex02;

import static org.junit.Assert.*;
import org.junit.Test;

public class HistoryTest {
	
	public void testHistory(){
		BankAccount test = new BankAccount(1,1000);
		assertNull(test.history());		//まだ一回も処理を行っていない場合
		test.withdraw(500);
		assertNotNull(test.history());
	}
	
	
	@Test
	public void testAdd() {
		BankAccount test = new BankAccount(1,1000);
		assertEquals(test.history().numHistory(),0);		//まだ一回も処理を行っていない場合
		test.withdraw(500);
		test.deposit(600);
		assertEquals(test.history().numHistory(),2);
	}

	@Test
	public void testNext() {
		BankAccount test = new BankAccount(1,1000);
		assertEquals(test.history().numHistory(),0);		//まだ一回も処理を行っていない場合
		test.withdraw(500);
		test.deposit(600);
		assertEquals(test.history().next().toString(),"1: deposit 600(balance: 1100)");
	}

}
