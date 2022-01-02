package com.example.banking.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class AccountTest {

	@Test
	@DisplayName("create account object successfuly")
	void createObjectSuccessfuly() {
		// 1. Test Fixture/Setup
		// 2. Call exercise method
		var acc = new Account("tr1", 10_000,AccountStatus.ACTIVE); // var sadece local variablelara uyuglayabiliriz.
		// 3. Verification
		assertEquals("tr1", acc.getIban());
		assertEquals(10_000, acc.getBalance());
		assertEquals(AccountStatus.ACTIVE, acc.getStatus());
		// 4.Tear-down//başlangıçta yarattığın nesneleri destroy et
		// (soket,veri tabanı bağlantıları kurulduysa kapatmak gibi)
	}
		
	@Test
	void setStatusSuccessfuly() {
		var acc = new Account("tr1", 10_000,AccountStatus.ACTIVE);
		acc.setStatus(AccountStatus.CLOSED);
		assertEquals(AccountStatus.CLOSED, acc.getStatus());		
	}

	@ParameterizedTest
	@CsvFileSource(resources = "deposit-fails.csv")
	@DisplayName("deposit negative amount to an accout should fail")
	void depositWithNegativeAmountShouldFail(String iban, double balance, double amount) throws Exception {
		// 1. Test Fixture/Setup
		var acc = new Account(iban, balance,AccountStatus.ACTIVE);
		// 2. Call exercise method
		var result = acc.deposit(amount);
		// 3. verification
		assertFalse(result);
		//assertEquals(balance,acc.getBalance()); //balance eşit mi ?
		//assertEquals(balance,acc.getBalance(),0.001); //balance 0.001 kadar yakın mı ?
		assertEquals(balance, acc.getBalance(), 0.001);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "withdraw-fails.csv")
	@DisplayName("withdraw negative amount from an accout should fail")
	void withdrawWithNegativeAmountShouldFail(String iban, double balance, double amount) throws Exception {
		// 1. Test Fixture/Setup
		var acc = new Account(iban, balance,AccountStatus.ACTIVE);
		// 2. Call exercise method
		var result = acc.withdraw(amount);
		// 3. verification
		assertFalse(result);
		assertEquals(balance, acc.getBalance());
	}

	@ParameterizedTest
	@CsvFileSource(resources = "withdraw-all.csv")
	@DisplayName("withdraw all balance from an accout should success")
	void withdrawAllAmountShouldSuccess(String iban, double balance) throws Exception {
		// 1. Test Fixture/Setup
		var acc = new Account(iban, balance,AccountStatus.ACTIVE);
		// 2. Call exercise method
		var allBalance = acc.withdrawAll();
		// 3. verification
		assertEquals(allBalance, balance);
		assertEquals(0, acc.getBalance());
	}

	
	@ParameterizedTest
	@CsvFileSource(resources = "withdraw-success.csv")
	@DisplayName("withdraw amount under balance from an accout should success")
	void withdrawUnderBalanceShouldSuccess(String iban, double balance,double amount) throws Exception {
		// 1. Test Fixture/Setup
		var acc = new Account(iban, balance,AccountStatus.ACTIVE);
		// 2. Call exercise method
		var result = acc.withdraw(amount);
		// 3. verification
		assertTrue(result);
		assertEquals(balance-amount, acc.getBalance());
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "withdraw-fails.csv")
	@DisplayName("withdraw negative amount or over balance from an accout should fail")
	void withdrawUnderNegativeAmountShouldFail(String iban, double balance,double amount) throws Exception {
		// 1. Test Fixture/Setup
		var acc = new Account(iban, balance,AccountStatus.ACTIVE);
		// 2. Call exercise method
		var result = acc.withdraw(amount);
		// 3. verification
		assertFalse(result);
		assertEquals(balance, acc.getBalance());
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "deposit-success.csv")
	@DisplayName("deposit positive amount to an accout should success")
	void depositWithPositiveAmountShouldSuccess(String iban, double balance, double amount) throws Exception {
		// 1. Test Fixture/Setup
		var acc = new Account(iban, balance,AccountStatus.ACTIVE);
		// 2. Call exercise method
		var result = acc.deposit(amount);
		// 3. verification
		assertTrue(result);
		assertEquals(balance + amount, acc.getBalance());
	}

	@Test
	void toStringShouldStartWithAccount() throws Exception {
		// 1. Test Fixture/Setup
		var acc = new Account("tr1", 10_000,AccountStatus.ACTIVE);
		// 2. Call exercise method
		// 3. verification
		assertTrue(acc.toString().startsWith("Account"));
	}
}
