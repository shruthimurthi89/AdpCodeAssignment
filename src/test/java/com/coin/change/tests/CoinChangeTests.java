package com.coin.change.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.home.coin.change.ChangeMachine;
import com.service.coin.change.CMHandler;

@SpringBootTest(classes = ChangeMachine.class)
public class CoinChangeTests {

	@Autowired
	CMHandler handler;

	
	@Test
	public void testError() {
		String result = handler.handle("ydfrdhfds");
		assertEquals("Transaction failed.", result);
		
	}
	
	@Test
	public void testSuccess() {
		String result = handler.handle("37");
		assertEquals("{nickel=40, dime=100, quarters=100}", result);
		
	}

}
