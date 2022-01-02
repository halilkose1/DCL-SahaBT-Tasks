package com.example.banking.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class BankTest {

	@Test
	void createObjectSuccessfuly() {
		// 1. Test Fixture/Setup
		// 2. Call exercise method
		var bank = new Bank(1,"garantiBbva",BankType.PRIVATE); 
		// 3. Verification
		assertEquals(1, bank.getId());
		assertEquals("garantiBbva", bank.getCommercialName());
		assertEquals(BankType.PRIVATE, bank.getType());
	}
	
	@Test
	void setCommercialNameSuccessfuly() {
		// 1. Test Fixture/Setup
		// 2. Call exercise method
		var bank = new Bank(1,"",BankType.PRIVATE); 
		// 3. Verification
		bank.setCommercialName("garantiBbva");
		assertEquals("garantiBbva", bank.getCommercialName());
	}
	
	@Test
	void createAndGetCustomerSuccessfuly(){
		var bank = new Bank(1,"garantiBbva",BankType.PRIVATE);
		List<Customer> createdcustomer = new ArrayList<>(); 
		createdcustomer.add(bank.createCustomer("40189661432", "jack shephard"));
		createdcustomer.add(bank.createCustomer("16533773038", "kate austen"));
		var result=bank.getCustomers().contains(createdcustomer.get(0));
		var result1=bank.getCustomers().contains(createdcustomer.get(1));
		assertTrue(result);
		assertTrue(result1);
	}
	
	@Test
	void findCustomerByIdentitySuccessfuly(){
		var bank = new Bank(1,"garantiBbva",BankType.PRIVATE);
		List<Customer> createdcustomer = new ArrayList<>(); 
		createdcustomer.add(bank.createCustomer("40189661432", "jack shephard"));
		var result=bank.findCustomerByIdentity("40189661432");
		assertTrue(result.get().toString().contains("40189661432"));
	
	}

}
