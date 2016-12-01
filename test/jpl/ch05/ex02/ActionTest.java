package jpl.ch05.ex02;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActionTest {

	@Test
	public void testAction() {
		BankAccount test = new BankAccount(1,1000);
		test.withdraw(500);
		System.out.println(test.action().toString());
		assertEquals(test.action().toString(),"1: withdraw 500(balance: 500)");
	}

	@Test
	public void testToString() {
		BankAccount test = new BankAccount(1,1000);
		assertNull(test.action());		//まだ一回も処理を行っていない場合
		test.withdraw(500);
		System.out.println(test.action().toString());	//アクションを行った場合
		assertEquals(test.action().toString(),"1: withdraw 500(balance: 500)");
	}

}
